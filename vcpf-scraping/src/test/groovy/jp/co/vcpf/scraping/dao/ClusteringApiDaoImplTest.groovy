package jp.co.vcpf.scraping.service

import com.fasterxml.jackson.databind.ObjectMapper
import jp.co.vcpf.scraping.config.NextConnection
import jp.co.vcpf.scraping.dao.ClusteringApiDao
import jp.co.vcpf.scraping.dto.RequestClusteringDto
import jp.co.vcpf.scraping.dto.ScrapingItemDto
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import spock.lang.Specification


class ClusteringApiDaoImplTest extends Specification {
    static ClusteringApiDao clusteringApiDao

    def setup() {
        String path = "classpath:beans-vcpfscraping-service.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        clusteringApiDao = applicationContext.getBean(ClusteringApiDao.class)
        clusteringApiDao.nextConnection = GroovyMock(NextConnection)
    }

    RequestClusteringDto readJson(String json_no) {
        String fname = "src/test/groovy/jp/co/vcpf/scraping/dao/data/";
        File testData = new File(fname)
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File f)  { return f.isFile() && f.getName().endsWith("_" + json_no + ".json"); }
        };
        File[] files = testData.listFiles(filter);
        List<File> flist = Arrays.asList(files);
        List<ScrapingItemDto> itemList = new ArrayList<>();
        for (File f : flist) {
            String json_path = f.getPath();
            def jsonData = new File(json_path).text
            ScrapingItemDto item = new ObjectMapper().readValue(jsonData,ScrapingItemDto.class);
            itemList.add(item)
        }
        RequestClusteringDto requestClusteringDto = new RequestClusteringDto()
        requestClusteringDto.setItems(itemList)
        return requestClusteringDto
    }


    def "Json load"() {
        given:
        RequestClusteringDto requestDto = readJson(json_no);

        when:
        String result = clusteringApiDao.clustering(requestDto);

        then:
        1 * clusteringApiDao.nextConnection.getHostname() >> {
            return "http://localhost"
        }
        1 * clusteringApiDao.nextConnection.getPort() >> {
            return "8083"
        }
        1 * clusteringApiDao.nextConnection.getEndpoint() >> {
            return "/clustering"
        }

        notThrown(Exception)


        where:
        no | json_no
        "0" | "0"
        "1" | "1"
    }

}

