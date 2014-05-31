import java.util.ArrayList;
import java.util.List;


public class GildedRose {

	public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
	public static final String ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose";
	public static final String _5_DEXTERITY_VEST = "+5 Dexterity Vest";
	public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
	public static final String AGED_BRIE = "Aged Brie";
	public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
	public static final int SULFURAS_QUALITY = 80;
	public static final int MAX_QUALITY = 50;
	public static final int MIN_QUALITY = 0;
	
	public static void main(String[] args) 
	{
        System.out.println("OMGHAI!");
		
        GildedRose gildedRose = new GildedRose(makeItems());
		gildedRose.updateQuality();
	}

	private static List<Item> makeItems() 
	{
		List<Item> items = new ArrayList<>();
        items.add(new Item(_5_DEXTERITY_VEST, 10, 20));
        items.add(new Item(AGED_BRIE, 2, 0));
        items.add(new Item(ELIXIR_OF_THE_MONGOOSE, 5, 7));
        
		items.add(new Item(SULFURAS_HAND_OF_RAGNAROS, 0, SULFURAS_QUALITY));
        items.add(new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 20));
        items.add(new Item(CONJURED_MANA_CAKE, 3, 6));
        return items;
	}
	
	private List<Item> items = null;
	public GildedRose(List<Item> items) 
	{
		this.items = items;
	}

	
    public void updateQuality()
    {
        for(Item item : items)
        {
            ItemType type = getType(item);
            type.updateQuality(item);
        }
    }
    
    private ItemType getType(Item item)
    {
        switch(item.getName())
        {
            case AGED_BRIE: return ItemType.AGED_BRIE;
            case SULFURAS_HAND_OF_RAGNAROS: return ItemType.LEGENDARY;
            case BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT: return ItemType.BACKSTAGE_PASSES;
            default: return ItemType.NORMAL;
        }
    }

    public String toString() 
    {
    	StringBuilder builder = new StringBuilder().append(items.size()).append(" items : ");
    	for(Item item : items)
    	{
    		builder.append("Item [name=").append(item.getName()).append(", sellIn=")
			.append(item.getSellIn()).append(", quality=").append(item.getQuality())
			.append("]");
    	}
    	return builder.toString();
    }
    
   

}