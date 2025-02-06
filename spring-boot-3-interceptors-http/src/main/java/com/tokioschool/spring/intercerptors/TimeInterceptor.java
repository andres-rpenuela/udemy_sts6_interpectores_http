package com.tokioschool.spring.intercerptors;

import java.lang.reflect.Method;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.ReadableInstant;
import org.joda.time.base.BaseSingleFieldPeriod;
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

		
		DateTime effectiveDT = new DateTime(2025, 1, 9, 0, 0);
		DateTime expiredDT = new DateTime(2026, 4, 30, 0, 0);
		
		int days =  daysBetween(effectiveDT, expiredDT).getDays() + 1;
		
		System.out.println("start.between: "+ days);
		
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
		
		// DIFERENICA DE DIAS ENTRE DOS FECHAS CON JODA
		DateTime effectiveDT = new DateTime(2025, 1, 9, 0, 0);
		DateTime expiredDT = new DateTime(2026,4,30,0,0);
		
		int days = daysBetween(effectiveDT, expiredDT).getDays() + 1;
		System.out.println("EffectiveDT= "+effectiveDT);
		System.out.println("ExpiredDT= "+expiredDT);
		System.out.println(" daysBetween(effectiveDT, expiredDT).getDays() + 1= "+days);
	}
	
	/**
     * Creates a <code>Days</code> representing the number of whole days
     * between the two specified datetimes. This method corectly handles
     * any daylight savings time changes that may occur during the interval.
     *
     * @param start  the start instant, must not be null
     * @param end  the end instant, must not be null
     * @return the period in days
     * @throws IllegalArgumentException if the instants are null or invalid
     */
    public static Days daysBetween(ReadableInstant start, ReadableInstant end) {
        //int amount = BaseSingleFieldPeriod.between(start, end, DurationFieldType.days());
    	int amount = between(start, end, DurationFieldType.days());
        return Days.days(amount);
    }
    
    /**
     * Calculates the number of whole units between the two specified datetimes.
     *
     * @param start  the start instant, validated to not be null
     * @param end  the end instant, validated to not be null
     * @param field  the field type to use, must not be null
     * @return the period
     * @throws IllegalArgumentException if the instants are null or invalid
     */
    protected static int between(ReadableInstant start, ReadableInstant end, DurationFieldType field) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("ReadableInstant objects must not be null");
        }
        Chronology chrono = DateTimeUtils.getInstantChronology(start);
        int amount = field.getField(chrono).getDifference(end.getMillis(), start.getMillis());
        return amount;
    }
}
