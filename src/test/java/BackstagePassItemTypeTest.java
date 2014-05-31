import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BackstagePassItemTypeTest
{
    private static final int YESTERDAY = -1;
    private static final int IN_A_YEAR = 365;
    private static final int TOMORROW = 1;
    private static final int TODAY = 0;
    private Item item;
    
    @Test
    public void backstagePassesQualityIncreasesFromMinToMaxWhenSellInDateIsInTheFuture() throws Exception
    {
        item = backstagePass(sell(IN_A_YEAR), Quality.min());
        for(int quality = item.getQuality(); quality < Quality.max(); quality = item.getQuality())
        {
            updateQuality();
            assertEquals(quality + 1, item.getQuality());
        }
    }
    
    @Test
    public void backstagePassesQualityIncreaseTwiceAsFastInTheLastTenDays() 
    {
        item = backstagePass(sell(TODAY + ItemType.DOUBLE_QUALITY_THRESHOLD), Quality.min());
        updateQuality();
        assertEquals(Quality.min() + 2, item.getQuality());
    }
    
    @Test
    public void backstagePassesQualityIncreaseThreeTimesAsFastInTheLastFiveDays() 
    {
        item = backstagePass(sell(TODAY + ItemType.TRIPLE_QUALITY_THRESHOLD), Quality.min());
        updateQuality();
        assertEquals(Quality.min() + 3, item.getQuality());
    }
    
    @Test
    public void backstagePassesQualityIs0AfterTheSellInDate() 
    {
        item = backstagePass(sell(TODAY), Quality.max());
        updateQuality();
        assertEquals(YESTERDAY, item.getSellIn());
        assertEquals(Quality.min(), item.getQuality());
    }
    
    @Test
    public void backstagePassesQualityNeverGetsBeyondMax() 
    {
        item = backstagePass(sell(TOMORROW), Quality.max() - 1);
        updateQuality();
        assertEquals(TODAY, item.getSellIn());
        assertEquals(Quality.max(), item.getQuality());
    }
    
    private Item backstagePass(int sellIn, int quality)
    {
        return new Item(ItemNames.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, sellIn, quality);
    }

    private int sell(int sellIn)
    {
        return sellIn;
    }

    private void updateQuality()
    {
        ItemType.BACKSTAGE_PASSES.updateQuality(item);
    }
}
