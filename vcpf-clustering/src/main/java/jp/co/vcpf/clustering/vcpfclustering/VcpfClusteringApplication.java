package jp.co.vcpf.clustering.vcpfclustering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("beans-vcpfclustering-service.xml")
@SpringBootApplication
public class VcpfClusteringApplication {

	public static void main(String[] args) {
		SpringApplication.run(VcpfClusteringApplication.class, args);
	}
}
