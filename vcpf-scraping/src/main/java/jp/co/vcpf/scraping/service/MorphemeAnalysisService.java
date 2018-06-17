package jp.co.vcpf.scraping.service;

import jp.co.vcpf.scraping.dto.MorphemeDto;

import java.util.List;

public interface MorphemeAnalysisService {
    public List<MorphemeDto> morphemeAnalysis(String content);
}
