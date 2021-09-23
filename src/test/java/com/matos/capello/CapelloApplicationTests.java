package com.matos.capello;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = CapelloApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class CapelloApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
