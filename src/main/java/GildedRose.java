import java.util.ArrayList;
import java.util.List;


public class GildedRose 
{
	public static void main(String[] args) 
	{
        System.out.println("OMGHAI!");
		
        GildedRose gildedRose = new GildedRose(makeItems());
		gildedRose.updateQuality();
	}

	static List<Item> makeItems() 
	{
		List<Item> items = new ArrayList<>();
        items.add(new Item(ItemNames._5_DEXTERITY_VEST, 10, 20));
        items.add(new Item(ItemNames.AGED_BRIE, 2, Quality.min()));
        items.add(new Item(ItemNames.ELIXIR_OF_THE_MONGOOSE, 5, 7));
        
		items.add(new Item(ItemNames.SULFURAS_HAND_OF_RAGNAROS, 0, Quality.legendary()));
        items.add(new Item(ItemNames.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 20));
        items.add(new Item(ItemNames.CONJURED_MANA_CAKE, 3, 6));
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
            ItemType type = ItemNames.typeOf(item.getName());
            type.updateQuality(item);
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