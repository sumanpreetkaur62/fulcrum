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

;


@RunWith(value = Parameterized.class)
public class GenerateInvoiceTest {
	static Map<String, Double> basket3;
	static Map<String, Double> basket2;
	static Map<String, Double> basket1;

	static Map<String, Double> expectedOutputOfBasket3;
	static Map<String, Double> expectedOutputOfBasket2;
	static Map<String, Double> expectedOutputOfBasket1;
	
	private Billing billingTestObject;
	public Map<String, Double> inputBasket;
	public Map<String, Double> expectedBasket;

	// Constructor
	public GenerateInvoiceTest(Map<String, Double> inputBasket, Map<String, Double> expectedBasket) {
		billingTestObject = new Billing();
		this.inputBasket = inputBasket;
		this.expectedBasket = expectedBasket;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { basket1, expectedOutputOfBasket1 },
				{ basket2, expectedOutputOfBasket2 }, { basket3, expectedOutputOfBasket3 },

		});
	}

	// test to check generateInvoice();
	@Test
	public void generateInvoiceTestBilling() {
		Map<String, Double> actualOutput = billingTestObject.generateInvoice(inputBasket);
		System.out.println(actualOutput);
		System.out.println(expectedBasket + "expected");
		assertEquals(actualOutput, expectedBasket);

	}

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

		expectedOutputOfBasket1 = new LinkedHashMap<String, Double>();
		expectedOutputOfBasket1.put("1 book", 12.49);
		expectedOutputOfBasket1.put("1 Music CD", 16.49);
		expectedOutputOfBasket1.put("1 chocolate bar", 0.85);
		expectedOutputOfBasket1.put("SalesTax", 1.50);
		expectedOutputOfBasket1.put("Total", 29.83);

		expectedOutputOfBasket2 = new LinkedHashMap<String, Double>();
		expectedOutputOfBasket2.put("1 imported box of chocolates", 10.50);
		expectedOutputOfBasket2.put("1 imported bottle of perfume", 54.65);
		expectedOutputOfBasket2.put("SalesTax", 7.65);
		expectedOutputOfBasket2.put("Total", 65.15);

		expectedOutputOfBasket3 = new LinkedHashMap<String, Double>();
		expectedOutputOfBasket3.put("1 imported bottle of perfume", 32.19);
		expectedOutputOfBasket3.put("1 bottle of perfume", 20.89);
		expectedOutputOfBasket3.put("1 packet of headache pills", 9.75);
		expectedOutputOfBasket3.put("1 box of imported chocolates", 11.85);
		expectedOutputOfBasket3.put("SalesTax", 6.70);
		expectedOutputOfBasket3.put("Total", 74.68);
	}

}
