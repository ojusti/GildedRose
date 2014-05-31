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
		items = new String[] { GildedRose._5_DEXTERITY_VEST, GildedRose.AGED_BRIE, GildedRose.SULFURAS_HAND_OF_RAGNAROS, GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT };
		sellIns = new Integer[15];
		for(int i = 0; i < sellIns.length; i++)
		{
		    sellIns[i] = i - 1;
		}
		qualities = new Integer[] { GildedRose.SULFURAS_QUALITY, GildedRose.MAX_QUALITY, GildedRose.MAX_QUALITY - 1, GildedRose.MIN_QUALITY + 1, GildedRose.MIN_QUALITY };
	}
	@Test
	public void lockDown() throws Exception 
	{
		LegacyApprovals.LockDown(this, "updateQuality", items, sellIns, qualities);
	}
	
	
	public String updateQuality(String item, Integer sellIn, Integer quality) throws Exception
	{
	    if(item.equals(GildedRose.SULFURAS_HAND_OF_RAGNAROS) ? quality == GildedRose.SULFURAS_QUALITY && sellIn == 0
	                                                         : quality != GildedRose.SULFURAS_QUALITY) 
	    {
	        GildedRose gildedRose = new GildedRose(Collections.singletonList(new Item(item, sellIn, quality)));
	        gildedRose.updateQuality();
	        return gildedRose.toString();
	    }
	    return null;
	}
	
}
