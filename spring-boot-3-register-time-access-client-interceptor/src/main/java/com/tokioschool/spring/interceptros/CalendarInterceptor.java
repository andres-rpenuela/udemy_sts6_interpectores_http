package com.tokioschool.spring.interceptros;

import java.util.Calendar;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class CalendarInterceptor implements HandlerInterceptor{
	
	private final Environment enviroment;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Calendar calendar = Calendar.getInstance();
		
		int open = Integer.parseInt( enviroment.getProperty("confing.calendar.open", "4") );
		int close = Integer.parseInt( enviroment.getProperty("confing.calendar.close", "17") );
		
		int hour = calendar.get(calendar.HOUR_OF_DAY); // now hour
		
		StringBuilder message = new StringBuilder("Horario de atención al cliente: ");
		message.append("%d am., ".formatted(open));
		message.append("%d pm.".formatted(close));
		message.append("Hora actual: %d".formatted(hour));
		
		if(hour < open || hour > close ) {
			
			log.error(message.toString());
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValueAsString(message);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().write(message.toString());
			
			//response.sendError(HttpStatus.BAD_REQUEST.value(), message.toString());
			// shop end
			return false;
		}
		
		message.append("Gracias por su visita!");
		request.setAttribute("message", message.toString());
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
}
