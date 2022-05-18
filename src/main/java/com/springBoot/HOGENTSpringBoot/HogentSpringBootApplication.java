package com.springBoot.HOGENTSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import service.VoetbalService;
import service.VoetbalServiceImpl;
import validation.AankoopTicketValidation;

@SpringBootApplication
public class HogentSpringBootApplication implements WebMvcConfigurer
{
	
	public static void main(String[] args)
	{
		SpringApplication.run(HogentSpringBootApplication.class, args);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/403").setViewName("403");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		
		registry.addResourceHandler("/css/**").addResourceLocations("resources/css/");
	}
	
	@Bean
	public VoetbalService voetbalService()
	{
		return new VoetbalServiceImpl();
	}
	
	@Bean
	public AankoopTicketValidation aankoopTicketValidation()
	{
		return new AankoopTicketValidation();
	}
	
	@Bean
	public MessageSource messageSource()
	{
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
}
