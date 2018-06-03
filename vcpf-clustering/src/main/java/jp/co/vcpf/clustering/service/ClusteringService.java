package jp.co.vcpf.clustering.service;

import jp.co.vcpf.clustering.dto.RequestClusteringDto;
import jp.co.vcpf.clustering.dto.ResponseDto;

public interface ClusteringService {
    public ResponseDto clustering(RequestClusteringDto requestClusteringDto);
}
