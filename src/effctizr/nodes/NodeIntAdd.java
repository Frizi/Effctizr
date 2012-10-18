package effctizr.nodes;
import effctizr.DataInput;
import effctizr.DataOutput;
import effctizr.DataType;
import effctizr.Node;
import effctizr.types.DataInteger;

/**
 *
 * @author frizi
 */
public class NodeIntAdd extends Node
{
    public NodeIntAdd() {}

    @Override
    public String getHeader()
    {
        return "add integers";
    }

    @Override
    protected void initializeInterface()
    {        
        this.inputs.add(new DataInput(DataType.INTEGER, "a", new DataInteger(this).setValue(1) ));
        this.inputs.add(new DataInput(DataType.INTEGER, "b", new DataInteger(this).setValue(1) ));

        this.outputs.add(new DataOutput(new DataInteger(this), "a + b"));
    }

    @Override
    protected void update()
    {
        DataInteger inA = (DataInteger)this.getInput(0);
        DataInteger inB = (DataInteger)this.getInput(1);
        DataInteger out = (DataInteger)this.getOutput(0);
        
        out.setValue( inA.getValue() + inB.getValue() );
        
        this.onSelfReady();
    }
    
}
