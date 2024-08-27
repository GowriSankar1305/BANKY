package com.cipherbyte.banky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BankyAppConfig {

	@Bean
	ObjectMapper getObjectMapper()	{
		return new ObjectMapper();
	}
}
