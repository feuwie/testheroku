package com.simakov.diploma;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);
	}
}
