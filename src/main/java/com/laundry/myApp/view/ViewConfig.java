package com.laundry.myApp.view;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ViewConfig implements WebMvcConfigurer {
	
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/auth/auth-acess-denied").setViewName("/auth/auth-acess-denied");
	}

}
