import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LegendaryItemTypeTest
{
    private static final ItemType legendaryItems = ItemType.LEGENDARY;
    private Item item;
    
    @Test
    public void legendaryItemSellInAndQualityNeverChange() throws Exception
    {
        item = legendaryItem();
        int sellIn = item.getSellIn();
        updateQuality();
        assertEquals(GildedRose.SULFURAS_QUALITY, item.getQuality());
        assertEquals(sellIn, item.getSellIn());
    }

    private void updateQuality()
    {
        legendaryItems.updateQuality(item);
    }

    private Item legendaryItem()
    {
        return new Item(GildedRose.SULFURAS_HAND_OF_RAGNAROS, 0, GildedRose.SULFURAS_QUALITY);
    }
    
}
