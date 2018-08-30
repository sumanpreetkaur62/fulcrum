package com.assignment.FulcrumAssignment;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SalesTaxApplicationTest {
	Billing BillingTestObject;

	@Before
	public void initializeContent() {
		BillingTestObject = new Billing();
	}
	
	@Test 
	public void intialiseBasketItemTestForInvalidInput(){
		int input=10;
		assertEquals(null,BillingTestObject.intialiseBasketItem(input));
		
	}

	@Test
	public void calculateTotalShouldReturnCorrectResult() {
		Double var1 = 89.767676;
		Double var2 = 23.767676;
		Double expected = 113.54;
		assertEquals(expected, BillingTestObject.calculateTotal(var1, var2));
	}

	@Test
	public void calculateSalesTaxShouldReturnCorrectSalesTax() {

		Double inputPrice = 15.5;
		Double salesTax = 10.0;
		Double expectedValue = 1.55;
		Double actualValue = BillingTestObject.calculateSalesTax(inputPrice, salesTax);
		assertEquals("Sales Tax calculated is wrong::", expectedValue, actualValue, 0);
	}
}
