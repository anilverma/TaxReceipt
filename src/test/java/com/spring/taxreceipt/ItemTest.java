package com.spring.taxreceipt;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContextManager;

import com.spring.taxreceipt.item.Item;
import com.spring.taxreceipt.item.Types.ItemType;

@RunWith(Parameterized.class)
@SpringBootTest(classes = TaxReceiptApplication.class)
public class ItemTest {

	private TestContextManager testContextManager;

	private String description;
	private float price;
	private ItemType itemType;
	private int quantity;
	private float expectedPriceWithTax;

	public ItemTest(String desc, float aPrice, ItemType type, int itemQuantity,float costWithTax) {
		description = desc;
		price = aPrice;
		itemType = type;
		quantity = itemQuantity;
		expectedPriceWithTax = costWithTax;
	}

	@Before
	public void setUpContext() throws Exception {
		this.testContextManager = new TestContextManager(getClass());
		this.testContextManager.prepareTestInstance(this);
	}

	public static Object[][] ITEM_LIST = new Object[][] { { "book", 12.49f, ItemType.BOOK,1,12.49f },
			{ "Music CD", 14.99f, ItemType.OTHERS,1, 16.49f }, { "chocolate bar", 0.85f, ItemType.FOOD,1,0.85f },
			{ "imported box of chocolates", 10.00f, ItemType.IMPORTED_FOOD,1, 10.50f },
			{ "imported bottle of perfume", 47.50f, ItemType.IMPORTED_OTHERS,1, 54.65f },
			{ "imported bottle of perfume", 27.99f, ItemType.IMPORTED_OTHERS,1, 32.19f },
			{ "bottle of perfume", 18.99f, ItemType.OTHERS,1, 20.89f },
			{ "packet of pills", 9.75f, ItemType.MEDICAL,2, 19.5f },
			{ "imported box of chocolates", 11.25f, ItemType.IMPORTED_FOOD, 1, 11.85f } };

	private Item createItem(String description, float price,int quantity, ItemType itemType) {
		Item item = testContextManager.getTestContext().getApplicationContext().getBean(Item.class);
		item.setItemType(itemType);
		item.setItemPrice(price);
		item.setItemDescription(description);
		item.setItemQuantity(quantity);
		return item;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		Object[][] data = ITEM_LIST;
		return Arrays.asList(data);
	}

	@Test
	public void testItemPriceWithTax() {
		Item item = createItem(description, price, quantity,itemType);
		Assert.assertEquals("test failed for price with tax " + item.getItemDescription(), expectedPriceWithTax,
				item.getItemPriceWithTax(), 0.0f);
	}

}
