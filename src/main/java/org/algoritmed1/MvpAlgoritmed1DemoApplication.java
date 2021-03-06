package org.algoritmed1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Точка входу аплікації.
 * @author roman
 *
 */
@SpringBootApplication
@EnableScheduling
@ImportResource("classpath:config-app-spring.xml")
public class MvpAlgoritmed1DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvpAlgoritmed1DemoApplication.class, args);
	}

}
