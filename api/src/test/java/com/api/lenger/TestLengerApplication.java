package com.api.lenger;

import org.springframework.boot.SpringApplication;

public class TestLengerApplication {

	public static void main(String[] args) {
		SpringApplication.from(LengerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
