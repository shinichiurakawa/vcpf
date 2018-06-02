package jp.co.vcpf.scraping.dto;

import java.util.List;

public class RequestScrapingDto {
    private String searchTerms;
    private List<ScrapingItemDto> searchResultList;

    public String getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public List<ScrapingItemDto> getSearchResultList() {
        return searchResultList;
    }

    public void setSearchResultList(List<ScrapingItemDto> searchResultList) {
        this.searchResultList = searchResultList;
    }
}
