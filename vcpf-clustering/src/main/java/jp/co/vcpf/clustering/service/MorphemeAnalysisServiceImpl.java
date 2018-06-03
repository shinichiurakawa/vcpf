package jp.co.vcpf.clustering.service;

import jp.co.vcpf.clustering.dto.MorphemeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;

@Component
public class MorphemeAnalysisServiceImpl implements MorphemeAnalysisService {

    private static final Logger logger = LoggerFactory.getLogger(MorphemeAnalysisServiceImpl.class);

    Boolean word_filter(String target_word) {
        // http://e-words.jp/p/r-ascii.html
        Matcher matcher_a = Pattern.compile(".*([!-/]|[:-@]|[\\[-\\`]|[\\{-\\~]).*").matcher(target_word);
        Matcher matcher_b = Pattern.compile("(「|」|【|】|『|』|（|）)").matcher(target_word);
        if (matcher_a.matches()) {
            return true;
        } else if (matcher_b.matches()) {
            return true;
        } else {
            return false;
        }
    }

    int word_count(String key, String text) {
        try {
            int count = 0;
            Matcher matcher = Pattern.compile(key).matcher(text);
            while (matcher.find()) {
                count++;
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    Integer morpheme_filter(String token, String content) {
        Integer count = 0;
        int c_th = 3; // 出現回数がc_th以上の単語のみ登録する
        int l_th = 2; // 単語の長さがl_th以上の単語のみ登録する
        if (token.length() >= l_th) {
            count = word_count(token, content);
            if (count >= c_th) {
                return 0;
            }
        }
        return count;
    }

    public List<MorphemeDto> morphemeAnalysis(String content) {
        Tokenizer tokenizer = new Tokenizer();
        List<String> token_list = new ArrayList<>();
        List<MorphemeDto> morphemeList = new ArrayList<>();

        /*
         * scrapingしたページから名詞のみを抽出
         */
        try {
            // コンテンツを単語に分解し、名詞のみを抽出する
            for (Token token : tokenizer.tokenize(content)) {
                if (token.getPartOfSpeechLevel1().equals("名詞") && !this.word_filter(token.getSurface())) {
                    token_list.add(token.getSurface());
                }
            }
        } catch (Exception e) {
            //e.getStackTrace();
            e.printStackTrace();
        }

        /*
         * 選定基準によりフィルター
         *
         */
        token_list.stream().forEach(token -> {
            Integer count = morpheme_filter(token,content);
            if (count > 0) {
                MorphemeDto morpheme = new MorphemeDto();
                morpheme.setCount(count);
                morpheme.setWord(token);
                morphemeList.add(morpheme);
                // MorphemeDto.idは後で付与される
            }
        });

        return morphemeList;
    }
}

