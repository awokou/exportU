package com.server.exportU;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ExportUApplication {

	public static void main(String[] args) {

		SpringApplication.run(ExportUApplication.class, args);

		log.info("*******************Deployment******************");
	}
}
