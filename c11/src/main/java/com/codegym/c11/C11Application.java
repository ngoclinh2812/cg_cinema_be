package com.codegym.c11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan("com.codegym.c11.aspect")
public class C11Application {

	public static void main(String[] args) {
		SpringApplication.run(C11Application.class, args);
	}

}
