package jp.co.vcpf.api.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.vcpf.api.config.GcpConnection;
import jp.co.vcpf.api.entity.SearchResultEntity;
import jp.co.vcpf.api.entity.SearchResultItemEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CustomSearchApiDaoImpl implements CustomSearchApiDao {
    @Autowired
    GcpConnection gcpConnection;

    private String createQueryString(String keyword) throws UnsupportedEncodingException {
        String separator = "&";
        String binder = "=";
        String enc_keyword = URLEncoder.encode(keyword,"utf-8");
        String cx = "cx" + binder + gcpConnection.getCx();
        String key = "key" + binder + gcpConnection.getKey();
        String query_str = gcpConnection.getHostname() + gcpConnection.getEndpoint() +  "?q=" + enc_keyword + separator + cx + separator + key;
        return query_str;
    }

    public List<SearchResultItemEntity> search(String keyword) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        List<SearchResultItemEntity> searchResultItemEntityList = null;

        try {
            HttpGet request = new HttpGet(this.createQueryString(keyword));
            response = httpclient.execute(request);

            int status = response.getStatusLine().getStatusCode();
            System.out.println("HTTPステータス:" + status);
            //HTTPステータス:200

            if (status == HttpStatus.SC_OK){
                String jsonData = EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8);
                SearchResultEntity searchResultEntity = new ObjectMapper().readValue(jsonData,SearchResultEntity.class);
                searchResultItemEntityList = searchResultEntity.getItems();
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
                if (Objects.isNull(searchResultItemEntityList)) {
                    searchResultItemEntityList = new ArrayList<>();
                }
                return searchResultItemEntityList;
            }
        }
    }
}
