package com.homeworks.springbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

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
		public String postLocalDate(@RequestBody GetDateTime time) {
			return time.getTzone();
		}
	}
}
