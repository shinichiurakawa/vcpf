package jp.co.vcpf.scraping.vcpfscraping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("beans-vcpfscraping-service.xml")
@SpringBootApplication
public class VcpfScrapingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VcpfScrapingApplication.class, args);
	}
}
