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
		// aplica por defecto a todas, o indicar que solo aplique a
		//registry.addInterceptor(loggerInterceptor).addPathPatterns("/**");// lo que aplica por defecto, poner esto es opcional
		//registry.addInterceptor(loggerInterceptor).addPathPatterns("/api/some/**");// lo que aplica a todo lo que cuelque de /api/some
		//registry.addInterceptor(loggerInterceptor).addPathPatterns("/api/some/bar");// aplica solo a este handler
		registry.addInterceptor(loggerInterceptor).addPathPatterns("/api/some/bar","/api/some/foo");// aplica solo a estos dos hanler solo
		
		// a todas, menos a
		registry.addInterceptor(timeInterceptor).excludePathPatterns("/api/some/bar");
	}

}
