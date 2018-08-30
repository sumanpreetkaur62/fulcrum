package com.assignment.FulcrumAssignment;

public enum Category {
	
	ImportedDuty(5), SaleTax(10);
	
	private Double salesTax;

	Category(double tax) {
		this.salesTax = tax;
	}

	public Double getSalesTax() {
		return salesTax;
	}

}
