import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BackstagePassItemTypeTest
{
    private static final int YESTERDAY = -1;
    private static final int IN_A_YEAR = 365;
    private static final int TOMORROW = 1;
    private static final int TODAY = 0;
    private static final ItemType backstagePasses= ItemType.BACKSTAGE_PASSES;
    private Item item;
    
    @Test
    public void backstagePassesQualityIncreasesFromMinToMaxWhenSellInDateIsInTheFuture() throws Exception
    {
        item = backstagePasses(sell(IN_A_YEAR), GildedRose.MIN_QUALITY);
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
    public void backstagePassesQualityIncreaseTwiceAsFastInTheLastTenDays() 
    {
        item = backstagePasses(sell(TODAY + ItemType.DOUBLE_QUALITY_THRESHOLD + 1), GildedRose.MIN_QUALITY);
        updateQuality();
        assertEquals(GildedRose.MIN_QUALITY + 1, item.getQuality());
        updateQuality();
        assertEquals(GildedRose.MIN_QUALITY + 1 + 2, item.getQuality());
    }
    
    @Test
    public void backstagePassesQualityIncreaseThreeTimesAsFastInTheLastFiveDays() 
    {
        item = backstagePasses(sell(TODAY + ItemType.TRIPLE_QUALITY_THRESHOLD + 1), GildedRose.MIN_QUALITY);
        updateQuality();
        assertEquals(GildedRose.MIN_QUALITY + 2, item.getQuality());
        updateQuality();
        assertEquals(GildedRose.MIN_QUALITY + 2 + 3, item.getQuality());
    }
    
    @Test
    public void backstagePassesQualityIs0AfterTheSellInDate() 
    {
        item = backstagePasses(sell(TODAY), GildedRose.MAX_QUALITY);
        updateQuality();
        assertEquals(YESTERDAY, item.getSellIn());
        assertEquals(GildedRose.MIN_QUALITY, item.getQuality());
    }
    
    @Test
    public void backstagePassesQualityNeverGetsBeyondMax() 
    {
        item = backstagePasses(sell(TOMORROW), GildedRose.MAX_QUALITY - 1);
        updateQuality();
        assertEquals(TODAY, item.getSellIn());
        assertEquals(GildedRose.MAX_QUALITY, item.getQuality());
    }
    
    

    private void updateQuality()
    {
        backstagePasses.updateQuality(item);
    }

    private int sell(int sellIn)
    {
        return sellIn;
    }

    private Item backstagePasses(int sellIn, int quality)
    {
        return new Item(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, sellIn, quality);
    }
    
}
