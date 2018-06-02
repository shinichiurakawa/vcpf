package jp.co.vcpf.scraping.service;

import jp.co.vcpf.scraping.dao.ClusteringApiDao;
import jp.co.vcpf.scraping.dto.RequestClusteringDto;
import jp.co.vcpf.scraping.dto.ScrapingItemDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ScrapingServiceImpl {
    @Autowired
    ClusteringApiDao clusteringApiDao;

    public String scraping(List<ScrapingItemDto> scrapingItemDtoList) {
        for (ScrapingItemDto scrapItem : scrapingItemDtoList) {
            try {
                Document doc = Jsoup.connect(scrapItem.getLink()).get();
                scrapItem.setContent(doc.body().text());

            } catch (Exception e) {

            }

        }
        RequestClusteringDto requestClusteringDto = new RequestClusteringDto();
        String result = clusteringApiDao.clustering(requestClusteringDto);
        return result;
    }

}

