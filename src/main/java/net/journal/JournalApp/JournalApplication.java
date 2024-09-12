package net.journal.JournalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement//to achieve atomicity calls interface PlatformTransactionManager who in turn calls MongoTransactionManager
@EnableScheduling//to achieve scheduling using cron jobs
public class JournalApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);
		System.out.println(context.getEnvironment());//to print dev or prod env

	}

	@Bean
	public PlatformTransactionManager PlatformTransaction(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
