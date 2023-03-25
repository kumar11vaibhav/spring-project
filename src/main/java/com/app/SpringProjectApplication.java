package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class SpringProjectApplication.
 */
@SpringBootApplication
public class SpringProjectApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		System.out.println("Initializing application...");
		SpringApplication.run(SpringProjectApplication.class, args);
	}

}
