package br.com.fireware.bpchoque;


import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;




@SpringBootApplication
public class BpchoqueApplication {

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
	@Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }
	

	
	public static void main(String[] args) {
		SpringApplication.run(BpchoqueApplication.class, args);
			
	}
	
	 
	
	@Configuration
	public static class MvcConfig extends WebMvcConfigurerAdapter {
		
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addRedirectViewController("/", "/home");
		}
		
	}
	
	
}
