package jp.co.vcpf.scraping.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.vcpf.scraping.dao.ClusteringApiDao;
import jp.co.vcpf.scraping.dto.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScrapingServiceImpl implements ScrapingService {
    private static final Logger logger = LoggerFactory.getLogger(ScrapingServiceImpl.class);

    @Autowired
    MorphemeAnalysisService morphemeAnalysisService;
    //ClusteringApiDao clusteringApiDao;

    @Autowired
    ClusteringControlService clusteringControlService;

    public ResponseDto scraping(RequestScrapingDto requestScrapingDto) throws Exception {
        List<MorphemeAnalysisDto> morphemeAnalysisList = new ArrayList<>();

        /*
         * 全検索結果に対して、Jsoupによるscrapingを実行
         */
        for (ScrapingItemDto scrapItem : requestScrapingDto.getItems()) {
            try {
                Document doc = Jsoup.connect(scrapItem.getLink()).get();
                scrapItem.setContent(doc.body().text());
                /*
                List<MorphemeDto> morphemeDtoList = morphemeAnalysisService.morphemeAnalysis(doc.body().text());
                MorphemeAnalysisDto mAnalysis = new MorphemeAnalysisDto();
                mAnalysis.setMorphemeList(morphemeDtoList);
                mAnalysis.setScrapingItem(scrapItem);
                morphemeAnalysisList.add(mAnalysis);
                */
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }

        ResponseDto response = clusteringControlService.clustering(requestScrapingDto.getItems());

        // debug 用にdump
        //createJsonFile(requestScrapingDto.getItems());

        /*
         * scraping結果をclusteringする
         */
        /*
        RequestClusteringDto requestClusteringDto = new RequestClusteringDto();
        requestClusteringDto.setItems(requestScrapingDto.getItems());
        requestClusteringDto.setSearchTerms(requestScrapingDto.getSearchTerms());

        String result = clusteringApiDao.clustering(requestClusteringDto);
        */
        return response;
    }

    private void createJsonFile(List<ScrapingItemDto> scrapItemList) {
        Integer idx = 0;
        for (ScrapingItemDto item : scrapItemList) {
            ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String response_json = "";
            try {
                response_json = mapper.writeValueAsString(item);

            } catch (Exception e) {
//            throw new VcpfBusinessException("1","message");
            }
            writeJson(response_json,idx.toString());
            idx = idx + 1;
        }
    }
    private void writeJson(String searchResult,String ver) {
        String fname = "src/test/groovy/jp/co/vcpf/scraping/service/data/ClusteringData_" + ver + ".json";
        try {
            File file = new File(fname);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(searchResult);
            fileWriter.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}

