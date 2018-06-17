package jp.co.vcpf.scraping.service;

import jp.co.vcpf.scraping.dao.MorphemeAnalysisDao;
import jp.co.vcpf.scraping.dto.MorphemeAnalysisDto;
import jp.co.vcpf.scraping.dto.MorphemeDto;
import jp.co.vcpf.scraping.dto.ResponseDto;
import jp.co.vcpf.scraping.dto.ScrapingItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ClusteringControlServiceImpl implements ClusteringControlService {
    @Autowired
    MorphemeAnalysisService morphemeAnalysisService;

    @Autowired
    MorphemeAnalysisDao morphemeAnalysisDao;

    private static final Logger logger = LoggerFactory.getLogger(ClusteringControlServiceImpl.class);

    private Integer findMorphemeDicId(List<MorphemeDto> morphDic, String keyword) {
        for (MorphemeDto morpheme : morphDic) {
            if (Objects.equals(morpheme.getWord(),keyword)) {
                return morpheme.getId();
            }
        }
        return morphDic.size();
    }

    private void allocateMorphemeIdx(List<MorphemeDto> morphDic, List<MorphemeDto> morphemeDtoList) {
       for (MorphemeDto morpheme : morphemeDtoList) {
           Integer dic_size = morphDic.size();
           // 辞書から単語に割り当てられたidを取得
           Integer id = findMorphemeDicId(morphDic,morpheme.getWord());
           // 辞書から取得したidを設定
           morpheme.setId(id);
           // 未登録の単語であれば辞書に追加登録
           if (Objects.equals(dic_size,id)) {
               morphDic.add(morpheme);
           }
       }
    }

    public ResponseDto clustering(List<ScrapingItemDto> itemList) {
        logger.info("clustering [IN]");

        List<MorphemeDto> morphemeDic = new ArrayList<>();

        /*
         * 形態素解析
         */
        for (ScrapingItemDto item : itemList) {
            List<MorphemeDto> morphemeDtoList = morphemeAnalysisService.morphemeAnalysis(item.getContent());
            allocateMorphemeIdx(morphemeDic,morphemeDtoList);
            MorphemeAnalysisDto morphemeAnalysisDto = new MorphemeAnalysisDto();
            morphemeAnalysisDto.setScrapingItem(item);
            morphemeAnalysisDto.setMorphemeList(morphemeDtoList);
            // 形態素解析の結果をDBに登録
            morphemeAnalysisDao.addMorphemeAnalysis(morphemeAnalysisDto);
        }

        /*
         * クラスタリング対象を設定
         */

        /*
         * クラスタリング(python)
         */

        /*
         * クラスタリング結果の取得
         */

        /*
         * create response
         */
        ResponseDto responseDto = new ResponseDto();
        /*
        responseDto.setResponse(response);
        */
        logger.info("search [OUT]");
        return responseDto;
    }

}

