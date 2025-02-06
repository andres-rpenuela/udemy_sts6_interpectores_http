package com.tokioschool.spring.intercerptors;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component("loggerInterceptor")
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		final HandlerMethod handlerMethod = (HandlerMethod) handler; // the last parameter
		final Method method = handlerMethod.getMethod();
		
		log.info("Enviado request a "+ method.getName());

		return true;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		final HandlerMethod handlerMethod = (HandlerMethod) handler; // the last parameter
		final Method method = handlerMethod.getMethod();
		
		log.info("Recibido del request a "+ method.getName());
	}
}
