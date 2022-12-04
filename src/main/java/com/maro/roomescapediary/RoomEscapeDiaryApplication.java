package com.maro.roomescapediary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RoomEscapeDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomEscapeDiaryApplication.class, args);
	}

}
