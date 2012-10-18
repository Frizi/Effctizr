package effctizr.types;

import effctizr.Data;
import effctizr.DataType;
import effctizr.Node;
import java.awt.Color;

/**
 *
 * @author frizi
 */
public class DataInteger extends Data
{
    protected int value;
    
    public DataInteger(Node owner)
    {
        super(owner);
        this.value = 0;
    }
    
    @Override
    public DataType getType()
    {
        return DataType.INTEGER;
    }
    
    public DataInteger setValue(int val)
    {
        value = val;
        return this;
    }
    
    public int getValue() { return value; }

    @Override
    public Color getTypeColor()
    {
        return Color.BLUE;
    }
}
