package effctizr;

import effctizr.types.DataInteger;

/**
 *
 * @author frizi
 */
public class Effctizr {

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {
        //    2    1      4    3
        // (5 + 1) * ( (2 + 3) - 2) =? 18
        
        Node node1 = new NodeBuilder(NodeType.INT_MUL).build();
        Node node2 = new NodeBuilder(NodeType.INT_ADD).build();
        Node node3 = new NodeBuilder(NodeType.INT_SUB).build();
        Node node4 = new NodeBuilder(NodeType.INT_ADD).build();
        
        node2.beginDataUpdate();
        node3.beginDataUpdate();
        node4.beginDataUpdate();
        ((DataInteger)node3.getInput(1)).setValue(2);
        
        ((DataInteger)node2.getInput(0)).setValue(5);
        ((DataInteger)node2.getInput(1)).setValue(1);
        
        ((DataInteger)node4.getInput(0)).setValue(2);
        ((DataInteger)node4.getInput(1)).setValue(3);
        node2.endDataUpdate();
        node3.endDataUpdate();
        node4.endDataUpdate();
        
        node3.trySetInput(0, node4.getOutput(0));
        
        node1.trySetInput(0, node2.getOutput(0));
        node1.trySetInput(1, node3.getOutput(0));
        
        int a1 = ((DataInteger) node1.getInput(0)).getValue();
        int b1 = ((DataInteger) node1.getInput(1)).getValue();
        int o1 = ((DataInteger) node1.getOutput(0)).getValue();
        System.out.println( "node1: " + a1 + " * " + b1 + " = " + o1 );

        int a2 = ((DataInteger) node2.getInput(0)).getValue();
        int b2 = ((DataInteger) node2.getInput(1)).getValue();
        int o2 = ((DataInteger) node2.getOutput(0)).getValue();
        System.out.println( "node2: " + a2 + " + " + b2 + " = " + o2 );

        int a3 = ((DataInteger) node3.getInput(0)).getValue();
        int b3 = ((DataInteger) node3.getInput(1)).getValue();
        int o3 = ((DataInteger) node3.getOutput(0)).getValue();
        System.out.println( "node3: " + a3 + " - " + b3 + " = " + o3 );

        int a4 = ((DataInteger) node4.getInput(0)).getValue();
        int b4 = ((DataInteger) node4.getInput(1)).getValue();
        int o4 = ((DataInteger) node4.getOutput(0)).getValue();
        System.out.println( "node4: " + a4 + " + " + b4 + " = " + o4 );
        
    }
}
