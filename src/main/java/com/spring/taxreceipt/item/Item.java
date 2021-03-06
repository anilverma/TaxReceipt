package com.spring.taxreceipt.item;

import com.spring.taxreceipt.item.Types.ItemType;

public interface Item {
	
	public String getItemDescription();
	public void setItemDescription(String description);
	public float getItemPrice();
	public void setItemPrice(float price);
	public void setItemType(ItemType itemType);
	public void setItemQuantity(int quantity);
	public int getItemQuantity();

	public boolean isItemImported();
	public boolean isItemExempted();

	public float getItemPriceWithTax();
	public float getItemSaleTax();
}
