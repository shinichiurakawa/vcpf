package jp.co.vcpf.scraping.service;

import jp.co.vcpf.scraping.dto.ResponseDto;
import jp.co.vcpf.scraping.dto.ScrapingItemDto;

import java.util.List;

public interface ClusteringControlService {
    public ResponseDto clustering(List<ScrapingItemDto> itemList);
}
