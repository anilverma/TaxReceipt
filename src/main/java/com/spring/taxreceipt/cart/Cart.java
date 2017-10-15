package com.spring.taxreceipt.cart;

import com.spring.taxreceipt.item.Item;

public interface Cart {
	public void addItemToCart(Item item);
	public void calculateAndPrintReceiptWithTax();
	public float getTotalCost() ;
	public float getSalesTax() ;
}
