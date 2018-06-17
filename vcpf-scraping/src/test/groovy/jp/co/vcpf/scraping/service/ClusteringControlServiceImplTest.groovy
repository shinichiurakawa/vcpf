package jp.co.vcpf.scraping.service

import com.fasterxml.jackson.databind.ObjectMapper
import jp.co.vcpf.scraping.config.NextConnection
import jp.co.vcpf.scraping.dao.ClusteringApiDao
import jp.co.vcpf.scraping.dto.RequestClusteringDto
import jp.co.vcpf.scraping.dto.ResponseDto
import jp.co.vcpf.scraping.dto.ScrapingItemDto
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import spock.lang.Specification


class ClusteringControlServiceImplTest extends Specification {
    static ClusteringControlService clusteringControlService

    def setup() {
        String path = "classpath:beans-vcpfscraping-service.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        clusteringControlService = applicationContext.getBean(ClusteringControlService.class)
        //clusteringApiDao.nextConnection = GroovyMock(NextConnection)
    }

    List<ScrapingItemDto> readJson() {
        String fname = "src/test/groovy/jp/co/vcpf/scraping/dao/data/";
        File testData = new File(fname)
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File f)  { return f.isFile() && f.getName().endsWith(".json"); }
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
        return itemList;
    }


    def "Json load"() {
        given:
        List<ScrapingItemDto> itemList = readJson();

        when:
        ResponseDto response = clusteringControlService.clustering(itemList)

        then:
        /*
        1 * clusteringApiDao.nextConnection.getHostname() >> {
            return "http://localhost"
        }
        1 * clusteringApiDao.nextConnection.getPort() >> {
            return "8083"
        }
        1 * clusteringApiDao.nextConnection.getEndpoint() >> {
            return "/clustering"
        }
        */
        notThrown(Exception)

/*
        where:
        no | json_no
        "0" | "0"
        "1" | "1"
        */
    }

}

