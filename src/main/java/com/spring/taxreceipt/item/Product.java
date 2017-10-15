package com.spring.taxreceipt.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.spring.taxreceipt.item.Types.ItemType;
import com.spring.taxreceipt.tax.TaxCalculator;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Product implements Item{
	private float itemPrice;
	private String name;
	private ItemType itemType;
	private int itemQuantity;
	@Autowired
	private TaxCalculator calculator;

	@Override
	public String getItemDescription() {
		return name;
	}

	@Override
	public void setItemDescription(String itemName) {
		name = itemName;
	}

	@Override
	public float getItemPrice() {
		return itemPrice;
	}

	@Override
	public void setItemPrice(float price) {
		itemPrice = price;

	}

	@Override
	public void setItemType(ItemType type) {
		itemType = type;
	}

	@Override
	public boolean isItemImported() {
		return itemType.isImported();
	}

	@Override
	public boolean isItemExempted() {
		return itemType.isExempted();
	}

	@Override
	public float getItemPriceWithTax() {
		return getItemSaleTax() + (getItemPrice() * getItemQuantity());
	}

	@Override
	public float getItemSaleTax() {
		return (calculator.calculateTax(this));
	}

	@Override
	public String toString() {
		return itemQuantity + " " + name + " :" + getItemPriceWithTax();
	}

	@Override
	public void setItemQuantity(int quantity) {
		itemQuantity= quantity;
		
	}

	@Override
	public int getItemQuantity() {
		
		return itemQuantity;
	}
}
