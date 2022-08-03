package com.homeworks.springbasic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;


class GetDateTimeTest {
	SpringBasicApplication.BasicController appTest = new SpringBasicApplication.BasicController();
	@Test
	@DisplayName("Zulu time test")
	void getZuluTime() {
		String zuluTimeApp = appTest.getUTC();
		String zuluTimeTest = OffsetDateTime.now(ZoneOffset.UTC).toString();
		assertEquals(zuluTimeApp.split("\\.")[0], zuluTimeTest.split("\\.")[0]);
	}

	@Test
	@DisplayName("Eastern Time test")
	void getEasternTime() {
		GetDateTime newyorkTime = new GetDateTime("America/New_York");
		String newyorkTimeApp = newyorkTime.getTzone();
		String newyorkTimeTest = LocalDateTime.now(TimeZone.getTimeZone("America/New_York").toZoneId()).toString();
		assertEquals(newyorkTimeTest.split("\\.")[0], newyorkTimeApp.split("\\.")[0]);

	}

	@Test
	@DisplayName("California Time test")
	void getCaliforniaTime() {
		GetDateTime newyorkTime = new GetDateTime("PST");
		String newyorkTimeApp = newyorkTime.getTzone();
		String newyorkTimeTest = LocalDateTime.now(TimeZone.getTimeZone("PST").toZoneId()).toString();
		assertEquals(newyorkTimeTest.split("\\.")[0], newyorkTimeApp.split("\\.")[0]);

	}
}
