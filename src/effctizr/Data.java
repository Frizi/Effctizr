package effctizr;

import java.awt.Color;

/**
 *
 * @author frizi
 */
public abstract class Data
{
    protected Node owner;
    
    public abstract DataType getType();
    
    public Data(Node owner)
    {
        this.owner = owner;
    }
    
    public boolean isTypeOf(DataType checkType)
    {
        return this.getType() == checkType;
    }
    
    public abstract Color getTypeColor();
}