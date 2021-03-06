import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NormalItemTypeTest
{
    private static final int YESTERDAY = -1;
    private static final int IN_A_YEAR = 365;
    private static final int TODAY = 0;
    private Item item;
    
    @Test
    public void normalItemsQualityDecreasesFromMaxToMinWhenSellInDateIsInTheFuture() throws Exception
    {
        item = aNormalItem(sell(IN_A_YEAR), Quality.max());
        
        for(int quality = item.getQuality(); quality > Quality.min(); quality = item.getQuality())
        {
            updateQuality();
            assertEquals(quality - 1, item.getQuality());
        }
    }
    
    @Test
    public void normalItemsQualityDecreasesTwiceAsFastAfterTheSellInDate() 
    {
        item = aNormalItem(sell(TODAY), Quality.max());
        
        updateQuality();
        assertEquals(YESTERDAY, item.getSellIn());
        assertEquals(Quality.max() - 2, item.getQuality());
    }
    
    @Test
    public void normalItemsQualityNeverGetsBeyondMin() 
    {
        item = aNormalItem(sell(TODAY), Quality.min() + 1);
        
        updateQuality();
        assertEquals(YESTERDAY, item.getSellIn());
        assertEquals(Quality.min(), item.getQuality());
    }

    private Item aNormalItem(int sellIn, int quality)
    {
        return new Item("a normal item", sellIn, quality);
    }
    
    private int sell(int sellIn)
    {
        return sellIn;
    }
    
    private void updateQuality()
    {
        ItemType.NORMAL.updateQuality(item);
    }
}
