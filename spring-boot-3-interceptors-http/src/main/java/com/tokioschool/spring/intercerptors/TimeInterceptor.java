package com.tokioschool.spring.intercerptors;

import java.lang.reflect.Method;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component("timeInterceptor")
@Slf4j
public class TimeInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long start = System.currentTimeMillis();
		
		final HandlerMethod handlerMethod = (HandlerMethod) handler; // the last parameter
		final Method method = handlerMethod.getMethod();
		
		request.setAttribute("start", start);
		log.info("Enviado request a %s, start: %d".formatted( method.getName(), start));

		Random random = new Random();
		Thread.sleep( random.nextInt(	500));
		
		return true;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long start = (long) request.getAttribute("start");
		long end = System.currentTimeMillis();
		
		final HandlerMethod handlerMethod = (HandlerMethod) handler; // the last parameter
		final Method method = handlerMethod.getMethod();
			
		log.info("Recidido de request a %s, start: %d, end: %s, time: %d".formatted( method.getName(), start, end, end - start));
	
	}
	
}
