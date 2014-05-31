import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AgedBrieItemTypeTest
{
    private static final int YESTERDAY = -1;
    private static final int IN_A_YEAR = 365;
    private static final int TODAY = 0;
    private Item item;
    
    @Test
    public void agedBrieQualityIncreasesFromMinToMaxWhenSellInDateIsInTheFuture() throws Exception
    {
        item = agedBrie(sell(IN_A_YEAR), Quality.min());
        
        for(int quality = item.getQuality(); quality < Quality.max(); quality = item.getQuality())
        {
            updateQuality();
            assertEquals(quality + 1, item.getQuality());
        }
    }
    
    @Test
    public void agedBrieQualityIncreaseTwiceAsFastAfterTheSellInDate() 
    {
        item = agedBrie(sell(TODAY), Quality.min());
        
        updateQuality();
        assertEquals(YESTERDAY, item.getSellIn());
        assertEquals(Quality.min() + 2, item.getQuality());
    }
    
    @Test
    public void agedBrieQualityNeverGetsBeyondMax() 
    {
        item = agedBrie(sell(TODAY), Quality.max() - 1);
        
        updateQuality();
        assertEquals(YESTERDAY, item.getSellIn());
        assertEquals(Quality.max(), item.getQuality());
    }

    private Item agedBrie(int sellIn, int quality)
    {
        return new Item(ItemNames.AGED_BRIE, sellIn, quality);
    }
    
    private int sell(int sellIn)
    {
        return sellIn;
    }
    
    private void updateQuality()
    {
        ItemType.AGED_BRIE.updateQuality(item);
    }
}
