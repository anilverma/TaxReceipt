package com.spring.taxreceipt.tax;

import org.springframework.stereotype.Component;

import com.spring.taxreceipt.item.Item;
import com.spring.taxreceipt.tax.TaxDefinitions.TaxType;

@Component
public class ServiceTaxCalculator implements TaxCalculator{

	private static final float ROUNDOFF=0.05f;
	public float calculateTax(Item item) {
		return roundOffTax(getItemTaxType(item).getApplicableTax() * item.getItemPrice()*item.getItemQuantity());
	}

	private TaxType getItemTaxType(Item item) {
		if(item.isItemImported() && !item.isItemExempted()){
			return TaxType.BOTH;
		}else if( item.isItemImported() && item.isItemExempted()){
			return TaxType.IMPORTED;
		}else if( !item.isItemImported() && !item.isItemExempted()){
			return TaxType.BASIC;
		}
		return TaxType.NA;
	}
	private float roundOffTax(float tax){
		return (float) Math.ceil(tax/ROUNDOFF)*ROUNDOFF;
	}

}
