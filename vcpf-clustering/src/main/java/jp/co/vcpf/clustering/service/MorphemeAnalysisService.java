package jp.co.vcpf.clustering.service;

import jp.co.vcpf.clustering.dto.MorphemeDto;

import java.util.List;

public interface MorphemeAnalysisService {
    public List<MorphemeDto> morphemeAnalysis(String content);
}
