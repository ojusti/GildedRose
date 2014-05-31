import static org.junit.Assert.*;

import org.junit.Test;

public class NormalItemTypeTest
{
    private static final int YESTERDAY = -1;
    private static final int IN_A_YEAR = 365;
    private static final int TOMOROW = 1;
    private static final int TODAY = 0;
    private static final ItemType normalItems = ItemType.NORMAL;
    private Item item;
    
    @Test
    public void normalItemsQualityDecreasesFromMaxToMinWhenSellInDateIsInTheFuture() throws Exception
    {
        item = aNormalItem(sell(IN_A_YEAR), GildedRose.MAX_QUALITY);
        
        for(int quality = item.getQuality(); quality > GildedRose.MIN_QUALITY; quality = item.getQuality())
        {
            updateQuality();
            assertEquals(quality - 1, item.getQuality());
        }
        assertEquals(GildedRose.MIN_QUALITY, item.getQuality());
        updateQuality();
        assertEquals(GildedRose.MIN_QUALITY, item.getQuality());
    }
    
    @Test
    public void normalItemsQualityDecreasesTwiceAsFastAfterTheSellInDate() 
    {
        item = aNormalItem(sell(TOMOROW), GildedRose.MAX_QUALITY);
        
        updateQuality();
        assertEquals(TODAY, item.getSellIn());
        
        int quality = item.getQuality();
        updateQuality();
        assertEquals(YESTERDAY, item.getSellIn());
        assertEquals(quality - 2, item.getQuality());
    }
    
    @Test
    public void normalItemsQualityNeverGetsBeyondMin() 
    {
        item = aNormalItem(sell(TODAY), GildedRose.MIN_QUALITY + 1);
        
        updateQuality();
        assertEquals(YESTERDAY, item.getSellIn());
        assertEquals(GildedRose.MIN_QUALITY, item.getQuality());
    }

    private void updateQuality()
    {
        normalItems.updateQuality(item);
    }

    private int sell(int sellIn)
    {
        return sellIn;
    }

    private Item aNormalItem(int sellIn, int quality)
    {
        return new Item("a normal item", sellIn, quality);
    }
    
}
