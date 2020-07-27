package com.n2s;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class CityconnectTest {

	CityConnectingRoadApplication cr = new CityConnectingRoadApplication();

	/*
	 * positiveTest - happy path
	 */
	@Test
	void positiveTest() {

		System.out.println(cr.connectRoad("boston", "newark"));
		assertEquals(cr.connectRoad("boston", "newark"), "Yes");

	}

	/*
	 * negativeTest - invalid route , not available in the file
	 */
	@Test
	void negativeTest() {

		System.out.println(cr.connectRoad("michigan", "texas"));
		assertEquals(cr.connectRoad("michigan", "texas"), "No");

	}

}
