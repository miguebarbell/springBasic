package com.homeworks.springbasic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBasicApplicationTests {

	@Autowired
	SpringBasicApplication appTest;
	@Autowired
	private MockMvc mockMvc;


	@Test
	void contextLoads() {
//		assert(appTest != null);
		assertThat(appTest).isNotNull();
	}

	@Test
	@DisplayName("GET / response be 200 OK and compare the time difference less than 1 second")
	public void getAtRoot() throws Exception {
		String zuluTimeApp = OffsetDateTime.now(ZoneOffset.UTC).toString().replace("Z", "");
		MvcResult response = mockMvc.perform(get("/"))
		                            .andExpect(status().isOk())
		                            .andReturn();
		String zuluTimeTest = response.getResponse().getContentAsString().replace("Z", "");
		LocalDateTime ldtZuluTimeApp = LocalDateTime.parse(zuluTimeApp);
		LocalDateTime ldtZuluTimeTest = LocalDateTime.parse(zuluTimeTest);
		assertTrue(0 >= Duration.between(ldtZuluTimeApp, ldtZuluTimeTest).getSeconds());
	}

	@Test
	@DisplayName("POST / asking TZ of LA")
	public void askForLa() throws Exception {
		LocalDateTime losAngelesTime = LocalDateTime.now(TimeZone.getTimeZone("America/Los_Angeles").toZoneId());
		String response = mockMvc.perform(post("/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"tz\":  \"America/Los_Angeles\"}"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
//		System.out.println("losAngelesTime = " + losAngelesTime);
//		System.out.println("response = " + response);
		assertTrue(0 >= Duration.between(losAngelesTime, LocalDateTime.parse(response)).getSeconds());
	}

	@Test
	@DisplayName("POST / asking TZ of NY")
	public void askForNY() throws Exception {
		LocalDateTime newYorkTime = LocalDateTime.now(TimeZone.getTimeZone("America/New_York").toZoneId());
		String response = mockMvc.perform(post("/")
				                         .contentType(MediaType.APPLICATION_JSON)
				                         .content("{ \"tz\":  \"America/New_York\"}"))
		                         .andExpect(status().isOk())
		                         .andReturn().getResponse().getContentAsString();
//		System.out.println("newYorkTime = " + newYorkTime);
//		System.out.println("response = " + response);
		assertTrue(0 >= Duration.between(newYorkTime, LocalDateTime.parse(response)).getSeconds());
	}

	@Test
	@DisplayName("POST / to an nonexistent TZ, should return the UTC")
	public void toNonexistentTZ() throws Exception {
		String zuluTimeApp = OffsetDateTime.now(ZoneOffset.UTC).toString().replace("Z", "");
		String response = mockMvc.perform(post("/")
				                         .contentType(MediaType.APPLICATION_JSON)
				                         .content("{ \"tz\":  \"America/Moscow\"}"))
		                         .andExpect(status().isOk())
		                         .andReturn().getResponse().getContentAsString();
//		System.out.println("response = " + response);
//		System.out.println("zuluTimeApp = " + zuluTimeApp);
		LocalDateTime ltdResponse = LocalDateTime.parse(response);
		LocalDateTime ldtZuluTimeApp = LocalDateTime.parse(zuluTimeApp);
		assertTrue(0 >= Duration.between(ltdResponse, ldtZuluTimeApp).getSeconds());

	}


}
