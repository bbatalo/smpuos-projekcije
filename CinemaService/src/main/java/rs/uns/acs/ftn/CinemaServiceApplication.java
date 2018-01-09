package rs.uns.acs.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class CinemaServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CinemaServiceApplication.class);
		app.run(args);
	}
}
