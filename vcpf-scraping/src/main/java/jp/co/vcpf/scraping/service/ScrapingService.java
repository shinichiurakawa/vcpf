package jp.co.vcpf.scraping.service;

import jp.co.vcpf.scraping.dto.RequestScrapingDto;
import jp.co.vcpf.scraping.dto.ResponseDto;

import java.util.List;

public interface ScrapingService {
    public ResponseDto scraping(RequestScrapingDto requestScrapingDto) throws Exception;
}
