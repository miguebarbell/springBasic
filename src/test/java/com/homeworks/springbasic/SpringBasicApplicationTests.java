package com.homeworks.springbasic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBasicApplicationTests {

	@Autowired
	SpringBasicApplication appTest;

	@Test
	void contextLoads() {
		assert(appTest != null);
	}
}
