import java.util.Collections;

import org.approvaltests.legacycode.LegacyApprovals;
import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

	private String[] items;
	private Integer[] qualities;
	private Integer[] sellIns;
	@Before
	public void setUp() 
	{
		items = new String[] { GildedRose._5_DEXTERITY_VEST, GildedRose.AGED_BRIE, GildedRose.SULFURAS_HAND_OF_RAGNAROS, GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, GildedRose.CONJURED_MANA_CAKE };
		sellIns = new Integer[] { 11, 5, 0, -1};
		qualities = new Integer[] { GildedRose.SULFURAS_QUALITY, GildedRose.MAX_QUALITY, GildedRose.MAX_QUALITY - 1, GildedRose.MIN_QUALITY };
	}
	@Test
	public void lockDown() throws Exception 
	{
		LegacyApprovals.LockDown(this, "updateQuality", items, sellIns, qualities);
	}
	
	
	public String updateQuality(String item, Integer sellIn, Integer quality) throws Exception
	{
		GildedRose gildedRose = new GildedRose(Collections.singletonList(new Item(item, sellIn, quality)));
		gildedRose.updateQuality();
		return gildedRose.toString();
	}
	
}
