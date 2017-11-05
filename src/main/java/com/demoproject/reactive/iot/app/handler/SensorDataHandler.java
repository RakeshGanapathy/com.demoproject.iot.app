package com.demoproject.reactive.iot.app.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.demoproject.reactive.iot.app.models.SensorData;
import com.demoproject.reactive.iot.app.repository.intf.SensorDataRepository;

import reactor.core.publisher.Mono;

@Component
public class SensorDataHandler {

	private static final Logger LOGGER = LogManager.getLogger(SensorDataHandler.class);

	@Autowired
	private SensorDataRepository repository;

	@Autowired
	public SensorDataHandler(SensorDataRepository repository) {
		this.repository = repository;
	}

	/**
	 * Adds the sensor data
	 * 
	 * @param serverRequest
	 * @return {@link ServerResponse}
	 */
	public Mono<ServerResponse> addSensorData(ServerRequest serverRequest) {
		LOGGER.entry(serverRequest);
		Mono<SensorData> sensorData = serverRequest.bodyToMono(SensorData.class);
		Mono<ServerResponse> response = repository.addSensorValue(sensorData);
		LOGGER.traceExit(response);
		return response;
	}
}
