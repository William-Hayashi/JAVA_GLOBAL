package br.com.fiap.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API_Global_Solution", version = "1", description = "API desenvolvida para a Global Solution 2024 2"))
public class GlobalSolutionApplication {

	public static void main(String[] args) {

		SpringApplication.run(GlobalSolutionApplication.class, args);
	}

}
