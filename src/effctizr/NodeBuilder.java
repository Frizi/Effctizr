package effctizr;

import effctizr.nodes.NodeIntAdd;
import effctizr.nodes.NodeIntMul;
import effctizr.nodes.NodeIntSub;
import effctizr.nodes.NodeVecDot;

/**
 *
 * @author frizi
 */
public class NodeBuilder
{
    protected NodeType type;
    
    public NodeBuilder(NodeType type)
    {
        this.type = type;
    }
    
    public Node build()
    {
        Node node = null;
        switch(type)
        {
            case INT_ADD:
                node = new NodeIntAdd();
                break;
            case INT_SUB:
                node = new NodeIntSub();
                break;
            case INT_MUL:
                node = new NodeIntMul();
                break;
            case VEC_DOT:
                node = new NodeVecDot();
                break;
            default:  
                assert !true : "trying to build unknown node";
                break;
        }
        
        node.initializeInterface();
        node.update();
        return node;
    }
    
}
