package com.javashitang.sso.sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.javashitang.sso.sample.dao")
public class SsoSmapleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoSmapleApplication.class, args);
	}

}