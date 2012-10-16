/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package effctizr;

import effctizr.nodes.NodeIntAdd;
import effctizr.nodes.NodeIntMul;
import effctizr.nodes.NodeIntSub;

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
            default:  
                assert !true : "trying to build unknown node";
                break;
        }
        
        node.initializeInterface();
        node.assertInterfaceConsistency();
        node.update();
        return node;
    }
    
}
