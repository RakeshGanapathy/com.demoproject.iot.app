package com.demoproject.reactive.iot.app.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import com.demoproject.reactive.iot.app.handler.SensorDataHandler;

@Configuration
public class RoutesConfig {

	@Autowired
	SensorDataHandler sensorDataHandler;

	@Autowired
	public RoutesConfig(SensorDataHandler sensorDataHandler) {
		this.sensorDataHandler = sensorDataHandler;
	}

	@Bean
	public RouterFunction<?> routerFunction() {
		return route(POST("/api/sensor/add_sensor_value").and(accept(MediaType.APPLICATION_JSON)),
				sensorDataHandler::addSensorData);
	}

}
