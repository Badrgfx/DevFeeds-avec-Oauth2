package com.devfeeds;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;



@SpringBootApplication
@EnableResourceServer
public class DevFeedsApplication  {

	
	public static void main(String[] args) {
		SpringApplication.run(DevFeedsApplication.class, args);
		
	}



}
