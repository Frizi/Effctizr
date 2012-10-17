package effctizr.nodes;

import effctizr.DataInput;
import effctizr.DataOutput;
import effctizr.DataType;
import effctizr.Node;
import effctizr.types.DataFloat;
import effctizr.types.DataVector;

/**
 *
 * @author frizi
 */
public class NodeVecDot extends Node
{

    @Override
    public String getHeader()
    {
        return "dot product";
    }

    @Override
    protected void initializeInterface()
    {
        this.inputs.add(new DataInput(DataType.VECTOR3, "vec1", new DataVector(this) ));
        this.inputs.add(new DataInput(DataType.VECTOR3, "vec2", new DataVector(this) ));
        
        this.outputs.add(new DataOutput(new DataFloat(this), "dot"));
    }

    @Override
    protected void update()
    {
        DataVector inA = (DataVector)this.getInput(0);
        DataVector inB = (DataVector)this.getInput(1);
        DataFloat out = (DataFloat)this.getOutput(0);
        
        out.setValue( inA.getX() * inB.getX() + inA.getY() * inB.getY() + inA.getZ() * inB.getZ());
        
        this.onSelfReady();
    }
}
