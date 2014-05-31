import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LegendaryItemTypeTest
{
    private Item item;
    
    @Test
    public void legendaryItemSellInAndQualityNeverChange() throws Exception
    {
        item = legendaryItem();
        int sellIn = item.getSellIn();
        updateQuality();
        assertEquals(Quality.legendary(), item.getQuality());
        assertEquals(sellIn, item.getSellIn());
    }

    private Item legendaryItem()
    {
        return new Item(ItemNames.SULFURAS_HAND_OF_RAGNAROS, 0, Quality.legendary());
    }
    
    private void updateQuality()
    {
        ItemType.LEGENDARY.updateQuality(item);
    }
    
}
