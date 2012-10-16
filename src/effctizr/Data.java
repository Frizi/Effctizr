package effctizr;

/**
 *
 * @author frizi
 */
public abstract class Data
{
    protected Node owner;
    protected static DataType type;
    
    public boolean isTypeOf(DataType checkType)
    {
        return type == checkType;
    }
}