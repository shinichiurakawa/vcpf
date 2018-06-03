package jp.co.vcpf.scraping.service;

import jp.co.vcpf.scraping.dto.ScrapingItemDto;

import java.util.List;

public interface ScrapingService {
    public String scraping(List<ScrapingItemDto> scrapingItemDtoList) throws Exception;
}
