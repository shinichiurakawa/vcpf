package jp.co.vcpf.api.dto;

import jp.co.vcpf.api.entity.SearchResultItemEntity;

public class ScrapingItemDto {
    private String title;
    private String link;
    private String content;

    public void setSearchResultItem(SearchResultItemEntity foundItem) {
        this.title = foundItem.getTitle();
        this.link = foundItem.getLink();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
