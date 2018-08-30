package com.assignment.FulcrumAssignment;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;



@RunWith(value = Parameterized.class)
public class InitializeBasketTest {
	int basketNumber;
	Map<String, Double> expectedBasket;
	private Billing billingTestObject;
	static Map<String, Double> basket3;
	static Map<String, Double> basket2;
	static Map<String, Double> basket1;

	// contructor with input parameters
	public InitializeBasketTest(int basketNumber, Map<String, Double> expectedBasket) {
		billingTestObject = new Billing();
		this.basketNumber = basketNumber;
		this.expectedBasket = expectedBasket;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 1, basket1 }, { 2, basket2 }, { 3, basket3 },

		});
	}

	//test to check intialiseBasket();
	@Test
	public void intialiseBasketItemTest() {
		Map<String, Double> actualOutput = billingTestObject.intialiseBasketItem(basketNumber);
		assertEquals(actualOutput, expectedBasket);
	}

	// static block to initialize parameters
	static {
		basket1 = new LinkedHashMap<String, Double>();
		basket1.put("1 book", 12.49);
		basket1.put("1 Music CD", 14.99);
		basket1.put("1 chocolate bar", 0.85);

		basket2 = new LinkedHashMap<String, Double>();
		basket2.put("1 imported box of chocolates", 10.00);
		basket2.put("1 imported bottle of perfume", 47.50);

		basket3 = new LinkedHashMap<String, Double>();
		basket3.put("1 imported bottle of perfume", 27.99);
		basket3.put("1 bottle of perfume", 18.99);
		basket3.put("1 packet of headache pills", 9.75);
		basket3.put("1 box of imported chocolates", 11.25);
	}

}
