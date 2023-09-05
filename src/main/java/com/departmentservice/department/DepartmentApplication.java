package com.departmentservice.department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;

@SpringBootApplication
public class DepartmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentApplication.class, args);
	}

	@Bean
	public DaprClient getDaprClient() {
		return new DaprClientBuilder().build();
	}
}
