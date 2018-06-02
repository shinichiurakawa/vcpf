package jp.co.vcpf.api.dao;

import jp.co.vcpf.api.dto.RequestScrapingDto;

public interface ScrapingApiDao {
    public String scrap(RequestScrapingDto requestScrapingDto);
}
