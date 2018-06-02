package jp.co.vcpf.api.dto;

import jp.co.vcpf.api.entity.SearchResultItemEntity;

import java.util.List;

public class RequestScrapingDto {
    private List<SearchResultItemEntity> items;

    public void setItems(List<SearchResultItemEntity> items) {
        this.items = items;
    }

    public List<SearchResultItemEntity> getItems() {
        return items;
    }
}
