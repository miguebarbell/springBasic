package com.homeworks.springbasic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;


class GetDateTimeTest {
	SpringBasicApplication.BasicController appTest = new SpringBasicApplication.BasicController();

	/**
	 * check the UTC from the request and the computer be equal
	 */
	@Test
	@DisplayName("UTC")
	void getZuluTime() {
		String zuluTimeApp = appTest.getUTC();
		String zuluTimeTest = OffsetDateTime.now(ZoneOffset.UTC).toString();
		// comparing strings
		assertEquals(zuluTimeApp.split("\\.")[0], zuluTimeTest.split("\\.")[0]);
		// comparing LocalDateTime
		LocalDateTime ldtApp = LocalDateTime.parse(appTest.getUTC().replace("Z", ""));
		LocalDateTime ldtTest = LocalDateTime.parse(zuluTimeTest.replace("Z", ""));
		assertTrue(0 >= Duration.between(ldtApp, ldtTest).getSeconds());
	}

	/**
	 * check the difference between <strong>two LocalDateTime</strong> objects be less than a second.
	 */
	@Test
	@DisplayName("Eastern Time")
	void getEasternTime() {
		GetDateTime newyorkTime = new GetDateTime("America/New_York");
		LocalDateTime newyorkTimeApp = LocalDateTime.parse(newyorkTime.getTzone());
		LocalDateTime newyorkTimeTest = LocalDateTime.now(TimeZone.getTimeZone("America/New_York").toZoneId());
		assertTrue(0 >= Duration.between(newyorkTimeApp, newyorkTimeTest).getSeconds());
	}

	/**
	 * check the similarity of <strong>two Strings</strong> until the second.
	 */
	@Test
	@DisplayName("California Time")
	void getCaliforniaTime() {
		GetDateTime newyorkTime = new GetDateTime("PST");
		String newyorkTimeApp = newyorkTime.getTzone();
		String newyorkTimeTest = LocalDateTime.now(TimeZone.getTimeZone("PST").toZoneId()).toString();
		assertEquals(newyorkTimeTest.split("\\.")[0], newyorkTimeApp.split("\\.")[0]);

	}
}
