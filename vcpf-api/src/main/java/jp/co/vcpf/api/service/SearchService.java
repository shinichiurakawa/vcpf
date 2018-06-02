package jp.co.vcpf.api.service;

import jp.co.vcpf.api.dto.RequestSearchDto;
import jp.co.vcpf.api.dto.ResponseDto;

public interface SearchService {
    ResponseDto search(RequestSearchDto requestSearchDto);
}
