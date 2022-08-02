package com.homeworks.springbasic;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.TimeZone;

public class GetDateTime {

	private final String tzone;

	public GetDateTime(@JsonProperty("tz") String tz) {
		this.tzone = LocalDateTime.now(TimeZone.getTimeZone(tz).toZoneId()).toString();
		System.out.println("time = " + tz);
	}
	public String getTzone() {
		return tzone;
	}
}
