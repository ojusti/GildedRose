import static org.junit.Assert.*;

import org.junit.Test;

public class AgedBrieItemTypeTest
{
    private static final int YESTERDAY = -1;
    private static final int IN_A_YEAR = 365;
    private static final int TOMOROW = 1;
    private static final int TODAY = 0;
    private static final ItemType agedItems = ItemType.AGED_BRIE;
    private Item item;
    
    @Test
    public void agedBrieQualityIncreasesFromMinToMaxWhenSellInDateIsInTheFuture() throws Exception
    {
        item = agedBrie(sell(IN_A_YEAR), GildedRose.MIN_QUALITY);
        
        for(int quality = item.getQuality(); quality < GildedRose.MAX_QUALITY; quality = item.getQuality())
        {
            updateQuality();
            assertEquals(quality + 1, item.getQuality());
        }
        assertEquals(GildedRose.MAX_QUALITY, item.getQuality());
        updateQuality();
        assertEquals(GildedRose.MAX_QUALITY, item.getQuality());
    }
    
    @Test
    public void agedBrieQualityIncreaseTwiceAsFastAfterTheSellInDate() 
    {
        item = agedBrie(sell(TOMOROW), GildedRose.MIN_QUALITY);
        
        updateQuality();
        assertEquals(TODAY, item.getSellIn());
        
        int quality = item.getQuality();
        updateQuality();
        assertEquals(YESTERDAY, item.getSellIn());
        assertEquals(quality + 2, item.getQuality());
    }
    
    @Test
    public void agedBrieQualityNeverGetsBeyondMax() 
    {
        item = agedBrie(sell(TODAY), GildedRose.MAX_QUALITY - 1);
        
        updateQuality();
        assertEquals(YESTERDAY, item.getSellIn());
        assertEquals(GildedRose.MAX_QUALITY, item.getQuality());
    }

    private void updateQuality()
    {
        agedItems.updateQuality(item);
    }

    private int sell(int sellIn)
    {
        return sellIn;
    }

    private Item agedBrie(int sellIn, int quality)
    {
        return new Item(GildedRose.AGED_BRIE, sellIn, quality);
    }
    
}
