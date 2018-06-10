package jp.co.vcpf.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.vcpf.api.dao.CustomSearchApiDao;
import jp.co.vcpf.api.dao.ScrapingApiDao;
import jp.co.vcpf.api.dto.RequestScrapingDto;
import jp.co.vcpf.api.dto.RequestSearchDto;
import jp.co.vcpf.api.dto.ResponseDto;
import jp.co.vcpf.api.dto.ScrapingItemDto;
import jp.co.vcpf.api.entity.SearchResultEntity;
import jp.co.vcpf.api.entity.SearchResultItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchServiceImpl implements SearchService {
    private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    CustomSearchApiDao customSearchApiDao;

    @Autowired
    ScrapingApiDao scrapingApiDao;

    private List<SearchResultItemEntity> readJson() {
        StringBuilder builder = new StringBuilder();
        List<SearchResultItemEntity> searchResultItemEntityList = null;
        String fname = "/Users/urakawashinichi/IdeaProjects/test_data/GCPF_SearchResult.json";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fname));

            String string = reader.readLine();
            while (string != null){
                builder.append(string + System.getProperty("line.separator"));
                string = reader.readLine();
            }
            String jsonData = builder.toString();

            SearchResultEntity searchResultEntity = new ObjectMapper().readValue(jsonData,SearchResultEntity.class);
            searchResultItemEntityList = searchResultEntity.getItems();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return searchResultItemEntityList;
    }

    public ResponseDto search(RequestSearchDto requestSearchDto) {
        logger.info("search [IN]");
        /*
         * googleカスタムサーチを実行し、検索結果のリストを取得する
         */
        List<SearchResultItemEntity> searchResultItemEntityList = customSearchApiDao.search(requestSearchDto.getKeywords());
        //List<SearchResultItemEntity> searchResultItemEntityList = readJson();

        /*
         * 検索結果をscrapingサービスに渡すための形式変換
         */
        List<ScrapingItemDto> scrapingItemDtoList = new ArrayList<>();
        searchResultItemEntityList.stream().forEach(resultItemEntity->{
            ScrapingItemDto scrapingItem = new ScrapingItemDto();
            scrapingItem.setSearchResultItem(resultItemEntity);
            scrapingItemDtoList.add(scrapingItem);
        });

        /*
         * scraping request 作成
         */
        ResponseDto responseDto = new ResponseDto();
        RequestScrapingDto requestScrapingDto = new RequestScrapingDto();
        requestScrapingDto.setItems(scrapingItemDtoList);
        requestScrapingDto.setSearchTerms(requestSearchDto.getKeywords());

        /*
         * scraping
         */
        String response = scrapingApiDao.scrap(requestScrapingDto);
        responseDto.setResponse(response);
        logger.info("search [OUT]");
        return responseDto;
    }

}

