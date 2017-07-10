package com.tiagohs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tiagohs.model.dto.DtoConverter;

//Classe de Configuração da Aplicação, incluindo suas rotas

@Configuration
public class AdminConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	@Bean
	public DtoConverter dtoConverter() {
		return new DtoConverter();
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/admin/home").setViewName("/admin/home");
		registry.addViewController("/auth/login").setViewName("/auth/login");
	}
	
}
