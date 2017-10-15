package com.spring.taxreceipt.tax;

import com.spring.taxreceipt.item.Item;

public interface TaxCalculator {

	public float calculateTax(Item item);

}
