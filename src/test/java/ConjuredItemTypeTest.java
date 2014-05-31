import static org.junit.Assert.assertEquals;

import org.junit.Test;



public class ConjuredItemTypeTest
{
    private static final int YESTERDAY = -1;
    private static final int IN_A_YEAR = 365;
    private static final int TODAY = 0;
    private Item item;
    
    @Test
    public void conjuredItemsQualityDecreasesAtDoubleRateFromMaxToMinWhenSellInDateIsInTheFuture() throws Exception
    {
        item = aConjuredItem(sell(IN_A_YEAR), Quality.max());
        
        for(int quality = item.getQuality(); quality > Quality.min(); quality = item.getQuality())
        {
            updateQuality();
            assertEquals(quality - 2, item.getQuality());
        }
    }
    
    @Test
    public void conjuredItemsQualityDecreasesTwiceAsFastAfterTheSellInDate() 
    {
        item = aConjuredItem(sell(TODAY), Quality.max());
        
        int quality = item.getQuality();
        updateQuality();
        assertEquals(YESTERDAY, item.getSellIn());
        assertEquals(quality - 4, item.getQuality());
    }
    
    @Test
    public void conjuredItemsQualityNeverGetsBeyondMin() 
    {
        item = aConjuredItem(sell(TODAY), Quality.min() + 1);
        
        updateQuality();
        assertEquals(YESTERDAY, item.getSellIn());
        assertEquals(Quality.min(), item.getQuality());
    }

    private Item aConjuredItem(int sellIn, int quality)
    {
        return new Item(ItemNames.CONJURED_MANA_CAKE, sellIn, quality);
    }
    
    private int sell(int sellIn)
    {
        return sellIn;
    }

    private void updateQuality()
    {
        ItemType.CONJURED.updateQuality(item);
    }
}
