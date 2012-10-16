/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
        /*
         * in:
         ** 0: int A = 1
         ** 1: int B = 1
         * out:
         ** 0: int A+B;
         */
        
        this.inputs.add(new DataInput(DataType.INTEGER, "a"));
        this.inputs.add(new DataInput(DataType.INTEGER, "b"));
                
        this.defaults.add(new DataInteger(this).setValue(1));
        this.defaults.add(new DataInteger(this).setValue(1));
        
        this.outputs.add(new DataOutput(new DataInteger(this), "a+b"));
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
