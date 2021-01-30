package com.spring.flickrapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FlickrapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlickrapiApplication.class, args);
	}

}
