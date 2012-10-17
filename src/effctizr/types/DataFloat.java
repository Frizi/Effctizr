/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package effctizr.types;

import effctizr.Data;
import effctizr.DataType;
import effctizr.Node;
import java.awt.Color;

/**
 *
 * @author frizi
 */
public class DataFloat extends Data
{
    protected float value;
    
    public DataFloat(Node owner)
    {
        super(owner);
        this.value = 0.0f;
    }    

    @Override
    public DataType getType()
    {
        return DataType.FLOAT;
    }

    @Override
    public Color getTypeColor()
    {
        return Color.CYAN;
    }

    public void setValue(float value)
    {
        this.value = value;
    }

    public float getValue()
    {
        return value;
    }
}
