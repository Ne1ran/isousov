package com.example.MadelaPractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class MadelaPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MadelaPracticeApplication.class, args);

		System.out.println("Checkmate");
	}


}
