package com.example.demo;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;
import com.example.demo.interceptors.BasicAuthenInterceptor;

public class InterceptorConfiguration implements WebMvcConfigurer 
{
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
     registry.addInterceptor(new BasicAuthenInterceptor());
    }
}
