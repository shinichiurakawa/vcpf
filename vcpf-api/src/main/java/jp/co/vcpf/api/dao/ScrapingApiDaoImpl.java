package jp.co.vcpf.api.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.vcpf.api.config.NextConnection;
import jp.co.vcpf.api.dto.RequestScrapingDto;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Component
public class ScrapingApiDaoImpl implements ScrapingApiDao {
    @Autowired
    NextConnection nextConnection;
    private String createQueryString() {
        String end_point = nextConnection.getHostname() + nextConnection.getEndpoint();
        return end_point;
    }
    public String scrap(RequestScrapingDto requestScrapingDto) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost request = new HttpPost(this.createQueryString());
        ObjectMapper mapper = new ObjectMapper();

        CloseableHttpResponse response = null;

        try {
            String json = mapper.writeValueAsString(requestScrapingDto);
            request.setEntity(new StringEntity(json));

            response = httpclient.execute(request);

            int status = response.getStatusLine().getStatusCode();

            if (status == HttpStatus.SC_OK){
                String jsonData = EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8);
                return jsonData;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return "";
            }
        }
    }
}
