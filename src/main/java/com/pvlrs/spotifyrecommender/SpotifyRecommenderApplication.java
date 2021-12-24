package com.pvlrs.spotifyrecommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpotifyRecommenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotifyRecommenderApplication.class, args);
	}
}
