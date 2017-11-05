package com.demoproject.reactive.iot.app.client;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Random;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.ExchangeFunctions;

import com.demoproject.reactive.iot.app.models.SensorData;

import reactor.core.publisher.Mono;

public class SensorClient {

	private ExchangeFunction exchange = ExchangeFunctions.create(new ReactorClientHttpConnector());
	private static String HOST = "localhost";
	private static Integer PORT = 9095;

	public static void main(String[] args) throws Exception {
		SensorClient client = new SensorClient();
		client.createPerson();
	}

	public void createPerson() {
		URI uri = URI.create(String.format("http://%s:%d/api/sensor/add_sensor_value", HOST, PORT));
		Random random = new Random();
		SensorData data = new SensorData(1, getTimestamp(), random.nextInt(100));

		ClientRequest request = ClientRequest.method(HttpMethod.POST, uri).body(fromObject(data)).build();

		Mono<ClientResponse> response = exchange.exchange(request);

		System.out.println("Response Form Server :" + response.block().statusCode());
	}

	public static Timestamp getTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}
}
