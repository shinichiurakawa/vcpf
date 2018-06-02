package jp.co.vcpf.scraping.dao;

import jp.co.vcpf.scraping.dto.RequestClusteringDto;

public interface ClusteringApiDao {
    public String clustering(RequestClusteringDto requestClusteringDto);
}
