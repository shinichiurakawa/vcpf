package jp.co.vcpf.api.service

import com.fasterxml.jackson.databind.ObjectMapper
import jp.co.vcpf.api.config.NextConnection
import jp.co.vcpf.api.dao.CustomSearchApiDaoImpl
import jp.co.vcpf.api.dto.RequestSearchDto
import jp.co.vcpf.api.dto.ResponseDto
import jp.co.vcpf.api.entity.SearchResultEntity
import jp.co.vcpf.api.entity.SearchResultItemEntity
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import spock.lang.Specification


class SearchServiceImplTest extends Specification {
    static SearchServiceImpl searchService

    def setup() {
        String path = "classpath:beans-vcpfapi-service.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        searchService = applicationContext.getBean(SearchServiceImpl.class)
        searchService.customSearchApiDao = GroovyMock(CustomSearchApiDaoImpl)
        searchService.scrapingApiDao.nextConnection = GroovyMock(NextConnection)
    }

    void writeJson(String searchResult,String ver) {
        String fname = "src/test/groovy/jp/co/vcpf/api/service/data/GCPF_SearchResult_" + ver + ".json";
        try {
            File file = new File(fname);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(searchResult);
            fileWriter.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    def "Json load"() {
        given:
        String currentPath = "src/test/groovy/jp/co/vcpf/api/service"
        def RequestSearchDto requestSearchDto = new RequestSearchDto (
                keywords: keyword
        )

        when:
        ResponseDto search = searchService.search(requestSearchDto)

        then:
        1 * searchService.customSearchApiDao.search(_) >> {
            def jsonData = new File(currentPath + "/data/" + json_name).text
            SearchResultEntity searchResultEntity = new ObjectMapper().readValue(jsonData,SearchResultEntity.class);
            List<SearchResultItemEntity> searchResultItemEntityList = searchResultEntity.getItems();
            return searchResultItemEntityList
        }

        1 * searchService.scrapingApiDao.nextConnection.getHostname() >> {
            return "http://localhost"
        }
        1 * searchService.scrapingApiDao.nextConnection.getPort() >> {
            return "8082"
        }
        1 * searchService.scrapingApiDao.nextConnection.getEndpoint() >> {
            return "/scraping"
        }

        where:
        no | json_name | keyword
        "1" | "GCPF_SearchResult.json" | "MySQL+SpringBoot"
    }

}
