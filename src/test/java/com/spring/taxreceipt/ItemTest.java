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

import com.spring.taxreceipt.ItemDefinitions.ItemType;

@RunWith(Parameterized.class)
@SpringBootTest(classes = TaxReceiptApplication.class)
public class ItemTest {

	private TestContextManager testContextManager;

	private String description;
	private float price;
	private ItemType itemType;
	private float expectedPriceWithTax;

	public ItemTest(String desc, float aPrice, ItemType type, float costWithTax) {
		description = desc;
		price = aPrice;
		itemType = type;
		expectedPriceWithTax = costWithTax;
	}

	@Before
	public void setUpContext() throws Exception {
		this.testContextManager = new TestContextManager(getClass());
		this.testContextManager.prepareTestInstance(this);
	}

	public static Object[][] ITEM_LIST = new Object[][] { { "book", 12.49f, ItemType.BOOK, 12.49f },
			{ "Music CD", 14.99f, ItemType.OTHERS, 16.49f }, { "chocolate bar", 0.85f, ItemType.FOOD, 0.85f },
			{ "imported box of chocolates", 10.00f, ItemType.IMPORTED_FOOD, 10.50f },
			{ "imported bottle of perfume", 47.50f, ItemType.IMPORTED_OTHERS, 54.65f },
			{ "imported bottle of perfume", 27.99f, ItemType.IMPORTED_OTHERS, 32.19f },
			{ "bottle of perfume", 18.99f, ItemType.OTHERS, 20.89f },
			{ "packet of headache pills", 9.75f, ItemType.MEDICAL, 9.75f },
			{ "box of imported chocolates", 11.25f, ItemType.IMPORTED_FOOD, 11.85f } };

	private Item createItem(String description, float price, ItemType itemType) {
		Item item = testContextManager.getTestContext().getApplicationContext().getBean(Item.class);
		item.setItemType(itemType);
		item.setItemPrice(price);
		item.setItemDescription(description);
		return item;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		Object[][] data = ITEM_LIST;
		return Arrays.asList(data);
	}

	@Test
	public void testItemPriceWithTax() {
		Item item = createItem(description, price, itemType);
		Assert.assertEquals("test failed for price with tax " + item.getItemDescription(), expectedPriceWithTax,
				item.getItemPriceWithTax(), 0.0f);
	}

}
