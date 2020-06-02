package com.javashitang.sso.sample;

import com.javashitang.autoconfigure.sso.EnableSso;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSso
@SpringBootApplication
public class SsoSmapleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoSmapleApplication.class, args);
	}

}