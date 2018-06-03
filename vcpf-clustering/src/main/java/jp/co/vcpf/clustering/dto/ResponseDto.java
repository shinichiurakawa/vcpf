package jp.co.vcpf.clustering.dto;

public class ResponseDto {
    private Integer status;
    private Object response;

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Integer getStatus() {
        return status;
    }

    public Object getResponse() {
        return response;
    }
}
