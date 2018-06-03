package jp.co.vcpf.clustering.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.vcpf.clustering.dto.RequestClusteringDto;
import jp.co.vcpf.clustering.dto.ResponseDto;
import jp.co.vcpf.clustering.service.ClusteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClusteringController {
    @Autowired
    ClusteringService clusteringService;

    @PostMapping("/clustering")
    public String clustering(@RequestBody RequestClusteringDto requestClusteringDto) {
        ResponseDto responseDto = clusteringService.clustering(requestClusteringDto);
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

