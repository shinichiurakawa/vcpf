package jp.co.vcpf.api.service;

import jp.co.vcpf.api.dao.CustomSearchApiDao;
import jp.co.vcpf.api.dao.ScrapingApiDao;
import jp.co.vcpf.api.dto.RequestScrapingDto;
import jp.co.vcpf.api.dto.RequestSearchDto;
import jp.co.vcpf.api.dto.ResponseDto;
import jp.co.vcpf.api.dto.ScrapingItemDto;
import jp.co.vcpf.api.entity.SearchResultItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchServiceImpl implements SearchService {
    @Autowired
    CustomSearchApiDao customSearchApiDao;

    @Autowired
    ScrapingApiDao scrapingApiDao;

    private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

    public ResponseDto search(RequestSearchDto requestSearchDto) {
        logger.info("search [IN]");
        /*
         * googleカスタムサーチを実行し、検索結果のリストを取得する
         */
        List<SearchResultItemEntity> searchResultItemEntityList = customSearchApiDao.search(requestSearchDto.getKeywords());

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

