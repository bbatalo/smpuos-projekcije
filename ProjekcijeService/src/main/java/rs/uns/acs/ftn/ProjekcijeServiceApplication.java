package rs.uns.acs.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableAutoConfiguration
//@EnableDiscoveryClient
//@SpringBootApplication
@SpringCloudApplication
@EnableFeignClients
public class ProjekcijeServiceApplication {
	
	public static void main(String[] args) {
		
    	SpringApplication app = new SpringApplication(ProjekcijeServiceApplication.class);		
    	app.run(args);

	}
}
