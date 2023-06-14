package shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import shop.config.StorageProperties;
import shop.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class TechShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechShopApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return(args->{
			storageService.init();
		});
	}

}
