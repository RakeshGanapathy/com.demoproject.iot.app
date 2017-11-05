package com.demoproject.reactive.iot.app.repository.intf;

import org.springframework.web.reactive.function.server.ServerResponse;

import com.demoproject.reactive.iot.app.models.SensorData;

import reactor.core.publisher.Mono;

public interface SensorDataRepository {

	/**
	 * Method to add a sensor value and return server response
	 * 
	 * @param sensorData
	 * @return {@link ServerResponse}
	 */
	Mono<ServerResponse> addSensorValue(Mono<SensorData> sensorData);
}
