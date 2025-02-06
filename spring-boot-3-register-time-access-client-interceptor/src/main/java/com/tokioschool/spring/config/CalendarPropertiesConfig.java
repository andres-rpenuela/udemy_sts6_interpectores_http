package com.tokioschool.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value="classpath:calendar.properties")
public class CalendarPropertiesConfig {

}
