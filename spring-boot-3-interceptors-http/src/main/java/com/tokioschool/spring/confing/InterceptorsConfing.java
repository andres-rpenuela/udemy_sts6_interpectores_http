package com.tokioschool.spring.confing;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tokioschool.spring.intercerptors.LoggerInterceptor;
import com.tokioschool.spring.intercerptors.TimeInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InterceptorsConfing implements WebMvcConfigurer  {
	
	private final LoggerInterceptor loggerInterceptor;
	private final TimeInterceptor timeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggerInterceptor);
		registry.addInterceptor(timeInterceptor);
	}

}
