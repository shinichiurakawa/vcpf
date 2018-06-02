package jp.co.vcpf.api.entity;

import java.util.List;

public class SearchResultEntity {
    private String kind;
    private Object url;
    private Object queries;
    private Object context;
    private Object searchInformation;
    private List<SearchResultItemEntity> items;

    public void setSearchInformation(Object searchInformation) {
        this.searchInformation = searchInformation;
    }

    public Object getSearchInformation() {
        return searchInformation;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public void setQueries(Object queries) {
        this.queries = queries;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    public void setItems(List<SearchResultItemEntity> items) {
        this.items = items;
    }

    public String getKind() {
        return kind;
    }

    public Object getUrl() {
        return url;
    }

    public Object getQueries() {
        return queries;
    }

    public Object getContext() {
        return context;
    }

    public List<SearchResultItemEntity> getItems() {
        return items;
    }
}
