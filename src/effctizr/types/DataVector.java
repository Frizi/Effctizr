/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package effctizr.types;

import effctizr.Data;
import effctizr.DataType;
import effctizr.Node;
import java.awt.Color;
import java.util.Vector;

/**
 *
 * @author frizi
 */
public class DataVector extends Data
{
    protected float x;
    protected float y;
    protected float z;
    
    public DataVector(Node owner)
    {
        super(owner);
        x = y = z = 0;
    }

    @Override
    public DataType getType()
    {
        return DataType.VECTOR3;
    }

    @Override
    public Color getTypeColor()
    {
        return new Color(37, 138, 250);
    }
    
    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getZ()
    {
        return z;
    }
    
    public void setValue(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void setZ(float z)
    {
        this.z = z;
    }
}
