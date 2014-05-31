
/** @author JOP */
public class ItemNames
{
    public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
    public static final String ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose";
    public static final String _5_DEXTERITY_VEST = "+5 Dexterity Vest";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    
    public static ItemType typeOf(String itemName)
    {
        switch(itemName)
        {
            case AGED_BRIE: return ItemType.AGED_BRIE;
            case SULFURAS_HAND_OF_RAGNAROS: return ItemType.LEGENDARY;
            case BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT: return ItemType.BACKSTAGE_PASSES;
            case CONJURED_MANA_CAKE: return ItemType.CONJURED;
            default: return ItemType.NORMAL;
        }
    }
}
