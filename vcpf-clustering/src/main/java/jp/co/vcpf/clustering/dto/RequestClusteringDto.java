package jp.co.vcpf.clustering.dto;

import java.util.List;

public class RequestClusteringDto {
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

