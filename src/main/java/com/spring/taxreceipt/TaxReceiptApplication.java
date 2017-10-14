package com.spring.taxreceipt;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.spring.taxreceipt.Types.ItemType;

@SpringBootApplication
public class TaxReceiptApplication {
	enum ItemTypeList{
		BOOK("Book"),
		MUSIC_CD("Music CD"),
		CHOCOLATE("Chocolate"),
		PERFUME("Perfume"),
		PILLS("Headache Pills");
		private String itemName;
		private ItemTypeList( String name){
			itemName = name;
		}

		public String getItemName(){
			return itemName;
		}
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TaxReceiptApplication.class, args);
		Scanner input = new Scanner(System.in);
		ItemTypeList list[] = ItemTypeList.values();
		StringBuilder buffer  = new StringBuilder();
		int count=1;
		for(ItemTypeList item : list){
			buffer.append(count++).append(" :").append(item.getItemName()+"\n");
		}
		String productList = "Select Product : \n"+buffer.toString();
        ItemType[] itemTypes = ItemType.values();

        buffer.delete(0, buffer.length());
		for(ItemType itemType : itemTypes){
			buffer.append(itemType.ordinal()).append(" :").append(itemType.name()+"\n");
		}
		String itemTypeList ="Product type: \n"+buffer;

		Cart cart = ctx.getBean(Cart.class);
		while(true){
			System.out.println(productList);
			int product = input.nextInt();
			if(product == 0 ){
				break;
			}
			Item item = ctx.getBean(Item.class);
			item.setItemDescription(list[product-1].getItemName());
			System.out.println("Price");
            item.setItemPrice(input.nextFloat());
            System.out.println(itemTypeList);
            item.setItemType(itemTypes[input.nextInt()]);
            
			cart.addItemToCart(item);

		}

		cart.calculateAndPrintReceiptWithTax();
	}
	
}
