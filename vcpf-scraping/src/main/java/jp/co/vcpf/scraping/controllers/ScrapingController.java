package jp.co.vcpf.scraping.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.vcpf.scraping.dto.RequestScrapingDto;
import jp.co.vcpf.scraping.dto.ResponseDto;
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
        String response_json = "";
        try {
            ResponseDto response = scrapingService.scraping(requestScrapingDto);
            ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS);
            response_json = mapper.writeValueAsString(response);
        } catch (Exception e) {
            return "";
        }
        return response_json;
    }
}
