package com.demoproject.reactive.iot.app.models;

import java.sql.Timestamp;

public class SensorData {
	private int id;
	private int sensorId;
	private Timestamp timestamp;
	private double value;

	public SensorData() {

	}

	public SensorData(int sensorId, Timestamp timestamp, double value) {
		super();
		this.sensorId = sensorId;
		this.timestamp = timestamp;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SensorData [id=" + id + ", sensorId=" + sensorId + ", timestamp=" + timestamp + ", value=" + value
				+ "]";
	}

}
