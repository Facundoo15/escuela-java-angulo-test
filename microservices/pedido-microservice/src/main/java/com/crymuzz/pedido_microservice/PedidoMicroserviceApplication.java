package com.crymuzz.pedido_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class PedidoMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidoMicroserviceApplication.class, args);
	}

}
