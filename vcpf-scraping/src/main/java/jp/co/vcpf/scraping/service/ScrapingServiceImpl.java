package jp.co.vcpf.scraping.service;

import jp.co.vcpf.scraping.dao.ClusteringApiDao;
import jp.co.vcpf.scraping.dto.RequestClusteringDto;
import jp.co.vcpf.scraping.dto.ScrapingItemDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScrapingServiceImpl implements ScrapingService {
    @Autowired
    ClusteringApiDao clusteringApiDao;

    public String scraping(List<ScrapingItemDto> scrapingItemDtoList) throws Exception {
        /*
         * 全検索結果に対して、Jsoupによるscrapingを実行
         */
        for (ScrapingItemDto scrapItem : scrapingItemDtoList) {
            try {
                Document doc = Jsoup.connect(scrapItem.getLink()).get();
                scrapItem.setContent(doc.body().text());

            } catch (Exception e) {
            }
        }
        /*
         * scraping結果をclusteringする
         */
        RequestClusteringDto requestClusteringDto = new RequestClusteringDto();

        String result = clusteringApiDao.clustering(requestClusteringDto);
        return result;
    }

}

