package jp.co.vcpf.api.entity;

public class SearchResultItemEntity {
    private String kind;
    private String title;
    private String htmlTitle;
    private String link;
    private String displayLink;
    private String snippet;
    private String htmlSnippet;
    private String cacheId;
    private String formattedUrl;
    private String htmlFormattedUrl;
    private Object pagemap;

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHtmlTitle(String htmlTitle) {
        this.htmlTitle = htmlTitle;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDisplayLink(String displayLink) {
        this.displayLink = displayLink;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public void setHtmlSnippet(String htmlSnippet) {
        this.htmlSnippet = htmlSnippet;
    }

    public void setCacheId(String cacheId) {
        this.cacheId = cacheId;
    }

    public void setFormattedUrl(String formattedUrl) {
        this.formattedUrl = formattedUrl;
    }

    public void setHtmlFormattedUrl(String htmlFormattedUrl) {
        this.htmlFormattedUrl = htmlFormattedUrl;
    }

    public void setPagemap(Object pagemap) {
        this.pagemap = pagemap;
    }

    public String getKind() {
        return kind;
    }

    public String getTitle() {
        return title;
    }

    public String getHtmlTitle() {
        return htmlTitle;
    }

    public String getLink() {
        return link;
    }

    public String getDisplayLink() {
        return displayLink;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getHtmlSnippet() {
        return htmlSnippet;
    }

    public String getCacheId() {
        return cacheId;
    }

    public String getFormattedUrl() {
        return formattedUrl;
    }

    public String getHtmlFormattedUrl() {
        return htmlFormattedUrl;
    }

    public Object getPagemap() {
        return pagemap;
    }
}
