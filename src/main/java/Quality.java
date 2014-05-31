
/** @author JOP */
public enum Quality
{
    LEGENDARY(80), MAX(50), MIN(0);
    
    private final int value;
    private Quality(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
    
    public static int min()
    {
        return MIN.getValue();
    }
    public static int max()
    {
        return MAX.getValue();
    }
    public static int legendary()
    {
        return LEGENDARY.getValue();
    }
    
    public static int constrain(int quality)
    {
        return quality == legendary() ? quality : Math.max(min(), Math.min(quality, max()));
    }
}
