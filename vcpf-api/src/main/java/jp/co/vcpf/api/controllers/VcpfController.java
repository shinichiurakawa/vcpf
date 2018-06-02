package jp.co.vcpf.api.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.vcpf.api.dto.RequestSearchDto;
import jp.co.vcpf.api.dto.ResponseDto;
import jp.co.vcpf.api.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VcpfController {
    @Autowired
    SearchService searchService;

    @PostMapping("/search")
    public String search(@RequestBody RequestSearchDto requestSearchDto) {
        ResponseDto responseDto = searchService.search(requestSearchDto);
        //
        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String response_json = "";
        try {
            response_json = mapper.writeValueAsString(responseDto);

        } catch (Exception e) {
//            throw new VcpfBusinessException("1","message");
        }
        return response_json;
    }
}

