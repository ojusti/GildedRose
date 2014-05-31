import java.util.Collections;

import org.approvaltests.legacycode.LegacyApprovals;
import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest 
{
	private String[] items;
	private Integer[] qualities;
	private Integer[] sellIns;
	@Before
	public void setUp() 
	{
		items = new String[] { ItemNames._5_DEXTERITY_VEST, ItemNames.AGED_BRIE, ItemNames.SULFURAS_HAND_OF_RAGNAROS, ItemNames.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, ItemNames.CONJURED_MANA_CAKE };
		sellIns = new Integer[15];
		for(int i = 0; i < sellIns.length; i++)
		{
		    sellIns[i] = i - 1;
		}
		qualities = new Integer[] { Quality.legendary(), Quality.max(), Quality.max() - 1, Quality.min() + 1, Quality.min() };
	}

	@Test
	public void lockDown() throws Exception 
	{
		LegacyApprovals.LockDown(this, "updateQuality", items, sellIns, qualities);
	}
	
	public String updateQuality(String item, Integer sellIn, Integer quality) throws Exception
	{
	    if(!isCoherent(item, sellIn, quality)) 
	    {
	        return null;
	    }
	    GildedRose gildedRose = new GildedRose(Collections.singletonList(new Item(item, sellIn, quality)));
	    gildedRose.updateQuality();
	    return gildedRose.toString();
	}
    private boolean isCoherent(String item, Integer sellIn, Integer quality)
    {
        return item.equals(ItemNames.SULFURAS_HAND_OF_RAGNAROS) ? quality == Quality.legendary() && sellIn == 0
	                                                            : quality != Quality.legendary();
    }
}
