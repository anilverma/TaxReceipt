package com.spring.taxreceipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.spring.taxreceipt.Types.ItemType;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Product implements Item{
	protected float itemPrice;
	private String name;
	private ItemType itemType;
	
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
		return getItemSaleTax() + getItemPrice();
	}

	@Override
	public float getItemSaleTax() {
		return (calculator.calculateTax(this));
	}

	@Override
	public String toString() {
		return 1 + " " + name + " :" + getItemPriceWithTax();
	}
}
