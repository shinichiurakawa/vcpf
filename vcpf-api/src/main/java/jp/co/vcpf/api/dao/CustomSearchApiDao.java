package jp.co.vcpf.api.dao;

import jp.co.vcpf.api.entity.SearchResultItemEntity;

import java.util.List;

public interface CustomSearchApiDao {
    public List<SearchResultItemEntity> search(String keyword);
}
