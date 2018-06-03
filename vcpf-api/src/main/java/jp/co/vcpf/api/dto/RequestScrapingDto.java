package jp.co.vcpf.api.dto;

import jp.co.vcpf.api.entity.SearchResultItemEntity;

import java.util.List;

public class RequestScrapingDto {
    private String searchTerms;
    private List<ScrapingItemDto> items;

    public String getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public List<ScrapingItemDto> getItems() {
        return items;
    }

    public void setItems(List<ScrapingItemDto> items) {
        this.items = items;
    }
}

