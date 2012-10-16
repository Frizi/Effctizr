/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package effctizr.types;

import effctizr.Data;
import effctizr.DataType;
import effctizr.Node;

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
}
