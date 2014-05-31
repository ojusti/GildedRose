public enum ItemType
{
    NORMAL
    {
        @Override protected void modifyQuality(Item item)
        {
            int quality = item.getQuality();
            
            quality--;
            if(item.getSellIn() < TODAY)
            {
                quality--;
            }
            item.setQuality(Quality.constrain(quality));
        }
    },
    AGED_BRIE
    {
        @Override protected void modifyQuality(Item item)
        {
            int quality = item.getQuality();
            
            quality++;
            if(item.getSellIn() < TODAY)
            {
                quality++;
            }
            item.setQuality(Quality.constrain(quality));
        }
    },
    BACKSTAGE_PASSES
    {
        @Override protected void modifyQuality(Item item)
        {
            int quality = item.getQuality();
            quality++;
            
            int sellIn = item.getSellIn();
            if(sellIn < DOUBLE_QUALITY_THRESHOLD)
            {
                quality++;
            }
            if(sellIn < TRIPLE_QUALITY_THRESHOLD)
            {
                quality++;
            }
            if(sellIn < TODAY)
            {
                quality = 0;
            }

            item.setQuality(Quality.constrain(quality));
        }
    },
    CONJURED
    {
        @Override protected void modifyQuality(Item item)
        {
            NORMAL.modifyQuality(item);
            NORMAL.modifyQuality(item);
        }
    },
    LEGENDARY 
    { 
        @Override protected void decrementSellIn(Item item) { } 
        @Override protected void modifyQuality(Item item) { }
    };

    public void updateQuality(Item item)
    {
        decrementSellIn(item);
        modifyQuality(item);
    }

    protected final static int TODAY = 0;
    protected final static int DOUBLE_QUALITY_THRESHOLD = 10;
    protected final static int TRIPLE_QUALITY_THRESHOLD = 5;
    protected void decrementSellIn(Item item)
    {
        item.setSellIn(item.getSellIn() - 1);
    }
    
    protected abstract void modifyQuality(Item item);
}
