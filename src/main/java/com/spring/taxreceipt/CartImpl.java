package com.spring.taxreceipt;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CartImpl implements Cart {
	private List<Item> itemList;
	private float saleTax;
	private float totalCost;
	private static final Format FORMATTER = new DecimalFormat("0.00");

	public CartImpl() {
		itemList = new ArrayList<Item>();
	}

	@Override
	public void addItemToCart(Item item) {
		itemList.add(item);
	}

	@Override
	public void calculateAndPrintReceiptWithTax() {
		resetCart();
		StringBuilder buffer = new StringBuilder();
		for (Item item : itemList) {
			buffer.append("\n").append(item.toString());
			totalCost += item.getItemPriceWithTax();
			saleTax += item.getItemSaleTax();
		}
		buffer.append("\nSales Tax:" + FORMATTER.format(saleTax));
		buffer.append("\nTotal :" + totalCost);
		System.out.println(buffer.toString());
	}

	private void resetCart() {
		totalCost = 0.0f;
		saleTax = 0.0f;
	}

	@Override
	public float getTotalCost() {
		return totalCost;
	}

	@Override
	public float getSalesTax() {
		return Float.valueOf(FORMATTER.format(saleTax));
	}

	public String toString(){
		StringBuilder buffer = new StringBuilder();
		for (Item item : itemList) {
			buffer.append("\n").append(item.toString());
		}
		return buffer.toString();
	}
}
