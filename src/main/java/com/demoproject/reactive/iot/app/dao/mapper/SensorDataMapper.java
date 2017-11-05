package com.demoproject.reactive.iot.app.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.demoproject.reactive.iot.app.models.SensorData;

/**
 * Contains all the mapper related methods to perform crud operations
 * 
 * @author sasi
 *
 */
@Mapper
public interface SensorDataMapper {

	/**
	 * Method to add a sensor data
	 * 
	 * @param sensorData
	 * @return {@link Integer}
	 */
	@Insert("insert into sensors_data(sensor_id,timestamp,value) values(#{sensorId},#{timestamp},#{value}) ")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	public int addSensorData(SensorData sensorData);
}
