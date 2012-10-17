package effctizr;

import effctizr.gui.NodePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author frizi
 */
public class Effctizr {

    /**
     * @param args the command line arguments
     * 
     */
    
    private static class TopmostMouseListener extends MouseInputAdapter
    {
        protected JLayeredPane parent;
        public TopmostMouseListener(JLayeredPane parent)
        {
            this.parent = parent;
        }
        
        @Override
        public void mousePressed(MouseEvent e)
        {
            super.mousePressed(e);
            this.parent.moveToFront(e.getComponent());
        }
        
    }
    
    private static class GuiRunnable implements Runnable
    {
        private final ArrayList<Node> nodes;

        public GuiRunnable(ArrayList<Node> nodes)
        {
            this.nodes = nodes;
        }
        
        @Override
        public void run()
        {
            JFrame f = new JFrame("Effctizr");
            
            JDesktopPane desk = new JDesktopPane();
            f.add(desk);
            desk.setSize(640, 480);
            desk.setBackground(Color.GRAY);
            desk.setVisible(true);
            
            for (Node node : nodes)
            {
                NodePanel testPanel = new NodePanel(node);                
                desk.add(testPanel);
                
                testPanel.addMouseListener(new TopmostMouseListener(desk));
                
            }
            
            f.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
            f.setSize(new Dimension(640, 480));
            f.setVisible(true);
        }
        
    }
    
    public static void main(String[] args) {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new NodeBuilder(NodeType.INT_ADD).build());
        nodes.add(new NodeBuilder(NodeType.INT_SUB).build());
        nodes.add(new NodeBuilder(NodeType.INT_MUL).build());
        nodes.add(new NodeBuilder(NodeType.VEC_DOT).build());
        
        SwingUtilities.invokeLater(new GuiRunnable(nodes));
        
    }
}
