package com.javashitang.sso.sample;

import com.javashitang.autoconfigure.sso.EnableSso;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSso
@SpringBootApplication
@MapperScan("com.javashitang.sso.sample.dao")
public class SsoSmapleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoSmapleApplication.class, args);
	}

}