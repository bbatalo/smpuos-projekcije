package rs.uns.acs.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class MovieServiceApplication {
	
	public static void main(String[] args) {
		
    	SpringApplication app = new SpringApplication(MovieServiceApplication.class);		
    	app.run(args);

	}
}
