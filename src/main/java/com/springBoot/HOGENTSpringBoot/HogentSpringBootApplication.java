package com.springBoot.HOGENTSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import service.VoetbalService;
import service.VoetbalServiceImpl;

@SpringBootApplication
public class HogentSpringBootApplication
{
	
	public static void main(String[] args)
	{
		SpringApplication.run(HogentSpringBootApplication.class, args);
	}
	
//	@Bean
//	public HelloService heloService()
//	{
//		return new HelloServiceImpl();
//	}
	
	@Bean
	public VoetbalService voetbalService()
	{
		return new VoetbalServiceImpl();
	}
}
