public enum ItemType
{
    NORMAL
    {
        public void updateQuality(Item item)
        {
            decrementSellIn(item);
            int quality = item.getQuality();
            
            quality--;
            if(item.getSellIn() < TODAY)
            {
                quality--;
            }
            item.setQuality(Math.max(quality, GildedRose.MIN_QUALITY));
        }
    },
    AGED_BRIE
    {
        @Override
        public void updateQuality(Item item)
        {
            decrementSellIn(item);
            int quality = item.getQuality();
            
            quality++;
            if(item.getSellIn() < TODAY)
            {
                quality++;
            }
            item.setQuality(Math.min(quality, GildedRose.MAX_QUALITY));
        }
    },
    BACKSTAGE_PASSES
    {
        @Override
        public void updateQuality(Item item)
        {
            decrementSellIn(item);
            int sellIn = item.getSellIn();
            
            int quality = item.getQuality();
            quality++;
            
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
            item.setQuality(Math.min(quality, GildedRose.MAX_QUALITY));

        }
    },
    CONJURED
    {
        @Override
        public void updateQuality(Item item)
        {
            // TODO Auto-generated method stub
            NORMAL.updateQuality(item);

        }
    },
    LEGENDARY { @Override public void updateQuality(Item item) { } };

    public abstract void updateQuality(Item item);

    protected final static int TODAY = 0;
    protected final static int DOUBLE_QUALITY_THRESHOLD = 10;
    protected final static int TRIPLE_QUALITY_THRESHOLD = 5;
    protected void decrementSellIn(Item item)
    {
        item.setSellIn(item.getSellIn() - 1);
    }

}
