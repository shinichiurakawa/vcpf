package jp.co.vcpf.scraping.controllers;

import jp.co.vcpf.scraping.dto.RequestScrapingDto;
import jp.co.vcpf.scraping.service.ScrapingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapingController {
    @Autowired
    ScrapingService scrapingService;

    @PostMapping("/scraping")
    public String scraping(@RequestBody RequestScrapingDto requestScrapingDto) {
        try {
            String result = scrapingService.scraping(requestScrapingDto.getSearchResultList());
            return result;
        } catch (Exception e) {
            return "";
        }
    }
}
