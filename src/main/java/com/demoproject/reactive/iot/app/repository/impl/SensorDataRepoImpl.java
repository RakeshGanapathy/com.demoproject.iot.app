package com.demoproject.reactive.iot.app.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.demoproject.reactive.iot.app.dao.mapper.SensorDataMapper;
import com.demoproject.reactive.iot.app.models.SensorData;
import com.demoproject.reactive.iot.app.repository.intf.SensorDataRepository;

import reactor.core.publisher.Mono;

@Component
public class SensorDataRepoImpl implements SensorDataRepository {

	@Autowired
	SensorDataMapper sensorDataMapper;

	private static final Logger LOGGER = LogManager.getLogger(SensorDataRepoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demoproject.reactive.iot.app.repository.intf.SensorDataRepository#
	 * addSensorValue(reactor.core.publisher.Mono)
	 */
	@Override
	public Mono<ServerResponse> addSensorValue(Mono<SensorData> sensorData) {
		LOGGER.entry(sensorData);
		// Get the data to pass to the mapper
		boolean isAdded = sensorDataMapper.addSensorData(sensorData.block()) > 0;
		LOGGER.traceExit(isAdded);
		Mono<ServerResponse> serverResponse;
		if (isAdded) {
			serverResponse = ServerResponse.ok().build();
		} else {
			serverResponse = ServerResponse.noContent().build();
		}
		return serverResponse;
	}

}
