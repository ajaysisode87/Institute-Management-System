package com.ajay.InstituteManagementSystemApp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Institute APIS",version="1.0.0",description ="InstituteManagementSystem with Security"))
public class InstituteManagementSystemAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstituteManagementSystemAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper ();
	}
}
