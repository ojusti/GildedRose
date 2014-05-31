import static org.junit.Assert.assertSame;

import org.junit.Test;



public class ItemNamesTest
{
    @Test
    public void verifyMappingItemNameToItemType() throws Exception
    {
        String[] NAMES = { ItemNames.SULFURAS_HAND_OF_RAGNAROS, ItemNames.AGED_BRIE, ItemNames.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, ItemNames.CONJURED_MANA_CAKE, "other item" };
        ItemType[] TYPES = { ItemType.LEGENDARY, ItemType.AGED_BRIE, ItemType.BACKSTAGE_PASSES, ItemType.CONJURED, ItemType.NORMAL };
        
        for(int i = 0; i < NAMES.length; i++)
        {
            assertSame(TYPES[i], ItemNames.typeOf(NAMES[i]));
        }
        
    }
}
