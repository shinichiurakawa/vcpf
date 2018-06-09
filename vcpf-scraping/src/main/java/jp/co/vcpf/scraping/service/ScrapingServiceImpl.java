package jp.co.vcpf.scraping.service;

import jp.co.vcpf.scraping.dao.ClusteringApiDao;
import jp.co.vcpf.scraping.dto.RequestClusteringDto;
import jp.co.vcpf.scraping.dto.RequestScrapingDto;
import jp.co.vcpf.scraping.dto.ScrapingItemDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScrapingServiceImpl implements ScrapingService {
    private static final Logger logger = LoggerFactory.getLogger(ScrapingServiceImpl.class);

    @Autowired
    ClusteringApiDao clusteringApiDao;

    public String scraping(RequestScrapingDto requestScrapingDto) throws Exception {
        /*
         * 全検索結果に対して、Jsoupによるscrapingを実行
         */
        for (ScrapingItemDto scrapItem : requestScrapingDto.getItems()) {
            try {
                Document doc = Jsoup.connect(scrapItem.getLink()).get();
                scrapItem.setContent(doc.body().text());

            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        /*
         * scraping結果をclusteringする
         */
        RequestClusteringDto requestClusteringDto = new RequestClusteringDto();
        requestClusteringDto.setItems(requestScrapingDto.getItems());
        requestClusteringDto.setSearchTerms(requestScrapingDto.getSearchTerms());

        String result = clusteringApiDao.clustering(requestClusteringDto);
        return result;
    }

}

