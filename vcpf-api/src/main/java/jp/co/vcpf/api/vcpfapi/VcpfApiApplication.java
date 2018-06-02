package jp.co.vcpf.api.vcpfapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("beans-vcpfapi-service.xml")
@SpringBootApplication
public class VcpfApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VcpfApiApplication.class, args);
	}
}
