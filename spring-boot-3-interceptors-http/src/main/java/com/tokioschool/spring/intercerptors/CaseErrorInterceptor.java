package com.tokioschool.spring.intercerptors;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component("caseErrorInterceptor")
@Slf4j
public class CaseErrorInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		final HandlerMethod handlerMethod = (HandlerMethod) handler; // the last parameter
		final Method method = handlerMethod.getMethod();
		
		log.info("pre hanle error: "+method.getName());
		
		Map<String,String> result = new HashMap<>();
		result.put("error","no titnes acceso a esta página!");
		result.put("date",new Date().toString());
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(result);
		response.setContentType("application/json");
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.getWriter().write(jsonString);
		
		//response.sendError(HttpStatus.BAD_REQUEST.value(), "Error pre handler: %s".formatted(method.getName()));
		
		
		return false;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		final HandlerMethod handlerMethod = (HandlerMethod) handler; // the last parameter
		final Method method = handlerMethod.getMethod();
		
		log.info("pre hanle error: "+method.getName());
	}
}
