package jp.co.vcpf.scraping.dto;

import java.util.List;

public class MorphemeAnalysisDto {
    private ScrapingItemDto scrapingItem;
    private List<MorphemeDto> morphemeList;

    public ScrapingItemDto getScrapingItem() {
        return scrapingItem;
    }

    public void setScrapingItem(ScrapingItemDto scrapingItem) {
        this.scrapingItem = scrapingItem;
    }

    public List<MorphemeDto> getMorphemeList() {
        return morphemeList;
    }

    public void setMorphemeList(List<MorphemeDto> morphemeList) {
        this.morphemeList = morphemeList;
    }
}
