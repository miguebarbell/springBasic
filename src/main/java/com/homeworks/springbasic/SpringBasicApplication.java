package com.homeworks.springbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

@SpringBootApplication
public class SpringBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicApplication.class, args);
	}
	@RestController
	public static class BasicController {
		@GetMapping("/")
		public String getUTC() {
			return OffsetDateTime.now(ZoneOffset.UTC).toString();
		}
		@PostMapping("/")
		public String postLocalDate(TimeZone tz) {
			return  LocalDateTime.now(tz.toZoneId()).toString();
		}
	}
}
