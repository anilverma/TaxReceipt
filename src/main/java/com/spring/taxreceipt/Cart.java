package com.spring.taxreceipt;

public interface Cart {
	public void addItemToCart(Item item);
	public void calculateAndPrintReceiptWithTax();
	public float getTotalCost() ;
	public float getSalesTax() ;
}
