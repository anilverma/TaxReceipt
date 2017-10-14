package com.spring.taxreceipt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContextManager;

import com.spring.taxreceipt.ItemDefinitions.ItemType;

@RunWith(Parameterized.class)
@SpringBootTest(classes = TaxReceiptApplication.class)
public class CartTest {
	private List<Item> itemList ;
	private float expectedTotalSalexTax;
	private float exptecdTotalCost;
	
	public static  TestContextManager testContextManager = new TestContextManager(CartTest.class);;
	
	public CartTest(List<Item> list , float totalCost , float totalSalexTax){
		itemList = list;
		exptecdTotalCost = totalCost;
		expectedTotalSalexTax = totalSalexTax;
	}
	
	@Before
	public void setUpContext() throws Exception {
		testContextManager = new TestContextManager(getClass());
		testContextManager.prepareTestInstance(this);
	}
	
	@Parameterized.Parameters
	public  static Collection<Object[]> data() {
	   Object[][] data = new Object[][] {
			   { createItemList(new Object[][]{ITEM_LIST[0], ITEM_LIST[1], ITEM_LIST[2]}), 29.83f , 1.50f},
			   { createItemList(new Object[][]{ITEM_LIST[3], ITEM_LIST[4]}), 65.15f , 7.65f},
			   { createItemList(new Object[][]{ITEM_LIST[5], ITEM_LIST[6], ITEM_LIST[7], ITEM_LIST[8]}), 74.68f , 6.70f} };
	   return Arrays.asList(data);
	 }
	 private static List<Item> createItemList(Object[][] data){
		 List<Item> list = new ArrayList<Item>();
		 for(Object[] item: data){
			 list.add(createItem((String)item[0],(Float)item[1], (ItemType)item[2]));
		 }
		 return list;
	 }

	public static Object[][] ITEM_LIST = new Object[][] { { "book", 12.49f, ItemType.BOOK, 12.49f },
			{ "Music CD", 14.99f, ItemType.OTHERS, 16.49f }, { "chocolate bar", 0.85f, ItemType.FOOD, 0.85f },
			{ "imported box of chocolates", 10.00f, ItemType.IMPORTED_FOOD, 10.50f },
			{ "imported bottle of perfume", 47.50f, ItemType.IMPORTED_OTHERS, 54.65f },
			{ "imported bottle of perfume", 27.99f, ItemType.IMPORTED_OTHERS, 32.19f },
			{ "bottle of perfume", 18.99f, ItemType.OTHERS, 20.89f },
			{ "packet of headache pills", 9.75f, ItemType.MEDICAL, 9.75f },
			{ "box of imported chocolates", 11.25f, ItemType.IMPORTED_FOOD, 11.85f } };

	private static  Item createItem(String description, float price, ItemType itemType) {
		Item item = testContextManager.getTestContext().getApplicationContext().getBean(Item.class);
		item.setItemType(itemType);
		item.setItemPrice(price);
		item.setItemDescription(description);
		return item;
	}
	
	
	 @Test
	 public void testCartCostAndTax(){

		Cart cart = testContextManager.getTestContext().getApplicationContext().getBean(Cart.class);
		for(Item item : itemList){
			 cart.addItemToCart(item);
		}
		 cart.calculateAndPrintReceiptWithTax();
		 Assert.assertEquals("test failed for sales tax" , expectedTotalSalexTax ,cart.getSalesTax(), 0.0f );
		 Assert.assertEquals("test failed for total cost" , exptecdTotalCost ,cart.getTotalCost(), 0.0f );
	 }	
}
