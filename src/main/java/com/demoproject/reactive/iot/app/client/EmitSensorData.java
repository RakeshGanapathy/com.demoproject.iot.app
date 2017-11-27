package com.demoproject.reactive.iot.app.client;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.ExchangeFunctions;

import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

public class EmitSensorData {
	private ExchangeFunction exchange = ExchangeFunctions.create(new ReactorClientHttpConnector());
	private static String URL = "https://us-central1-iot-data-process-analyze.cloudfunctions.net/emit-iot-data";

	public static void main(String[] args) throws Exception {
		EmitSensorData emitSensorData = new EmitSensorData();
		List<Sensor> sensors = emitSensorData.readSensors();
		emitSensorData.emit(sensors);
	}

	public List<Sensor> readSensors() throws FileNotFoundException {
		File file = new File("input.json");
		FileReader fr = new FileReader(file);
		StringBuilder json = new StringBuilder();
		String line = null;
		try {
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				json.append(line);
			}
			ObjectMapper mapper = new ObjectMapper();
			br.close();
			Sensors sensors = mapper.readValue(json.toString(), Sensors.class);
			return sensors.getSensors();

		} catch (IOException e) {
			System.out.println(e);
		}
		return new ArrayList<>();
	}

	public void emit(List<Sensor> sensors) {
		for (double i = 1; i <= Math.pow(2, 4); i *= 2) {
			for (double j = 1; j <= i; j++) {
				sensors.stream().forEach(sensor -> {
					sendToCloudFunction(sensor);
				});
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(e);
				}
			}
		}

	}

	public boolean sendToCloudFunction(Sensor sensor) {
		// ObjectMapper mapper = new ObjectMapper();
		// json = mapper.writeValueAsString();
		URI uri = URI.create(URL);
		ClientRequest request = ClientRequest.method(HttpMethod.POST, uri).
				body(fromObject(getSensorData(sensor)))
				.build();
		Mono<ClientResponse> response = exchange.exchange(request);
		System.out.println("Response Form Server :" + response.block().statusCode());
		return response.block().statusCode() == HttpStatus.OK;
	}

	public SensorData getSensorData(Sensor sensor) {
		SensorData sensorData = new SensorData();
		sensorData.setSensorId(sensor.getSensorId());
		sensorData.setTimestamp(getSystemTimestamp());
		sensorData.setType(sensor.getType());
		Random random = new Random();
		sensorData.setValue(random.nextInt(sensor.getMax() - sensor.getMin()) + sensor.getMin());
		return sensorData;
	}

	public Timestamp getSystemTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis() / 1000);
		return timestamp;
	}
}

class SensorData {
	private String sensorId;
	private Timestamp timestamp;
	private String type;
	private double value;

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SensorData [sensorId=" + sensorId + ", timestamp=" + timestamp + ", type=" + type + ", value=" + value
				+ "]";
	}

}

class Sensors {
	private List<Sensor> sensors;

	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	@Override
	public String toString() {
		return "Sensors [sensors=" + sensors + "]";
	}

}

class Sensor {
	private String sensorId;
	private String type;
	private int min;
	private int max;

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "Sensor [sensorId=" + sensorId + ", type=" + type + ", min=" + min + ", max=" + max + "]";
	}

}