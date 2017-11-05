/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demoproject.reactive.iot.app.client;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.demoproject.reactive.iot.app.handler.SensorDataHandler;
import com.demoproject.reactive.iot.app.repository.impl.SensorDataRepoImpl;
import com.demoproject.reactive.iot.app.repository.intf.SensorDataRepository;

import reactor.ipc.netty.http.server.HttpServer;

public class Server {

	public static final String HOST = "localhost";

	public static final int PORT = 9095;

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		server.startReactorServer();
		// server.startTomcatServer();

		System.out.println("Press ENTER to exit.");
		System.in.read();
	}

	public RouterFunction<ServerResponse> routingFunction() {
		SensorDataRepository repository = new SensorDataRepoImpl();
		SensorDataHandler handler = new SensorDataHandler(repository);
		return route(POST("/api/sensor/add_sensor_value").and(contentType(APPLICATION_JSON)), handler::addSensorData);
	}

	public void startReactorServer() throws InterruptedException {
		RouterFunction<ServerResponse> route = routingFunction();
		HttpHandler httpHandler = toHttpHandler(route);

		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
		HttpServer server = HttpServer.create(HOST, PORT);
		server.newHandler(adapter).block();
	}

}
