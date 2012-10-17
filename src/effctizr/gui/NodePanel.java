/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package effctizr.gui;

import effctizr.DataInput;
import effctizr.DataOutput;
import effctizr.Node;
import effctizr.NodeEvent;
import effctizr.NodeObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author frizi
 */
public class NodePanel extends JPanel implements MouseMotionListener, MouseListener, NodeObserver
{
    protected Node linkedNode;
    protected Point dragPos;
    public NodePanel(Node node)
    {
        super(new GridBagLayout());
        linkedNode = node;
        JLabel header = new JLabel(node.getHeader());
        header.setForeground(Color.LIGHT_GRAY);
        this.setBackground(Color.DARK_GRAY);
        
        /* 
         * layout:
         * 4 cols x n+1 rows
         * n = max(inputs,outputs)
         * row1: header
         * row <2,n>
         * col1: inputs
         * col2: inputs' labels
         * col3: outputs' labels
         * col4: outputs
         */
        
        GridBagConstraints hc = new GridBagConstraints();
        hc.gridx = 0;
        hc.gridy = 0;
        hc.gridwidth = 4;
        this.add(header, hc);
        
        ArrayList<DataInput> inputs = node.getInputList();
        ArrayList<DataOutput> outputs = node.getOutputList();
        
        int i = 0;
        
        for (DataInput dataInput: inputs)
        {
            i++;
            GridBagConstraints activeConst = new GridBagConstraints();
            GridBagConstraints labelConst = new GridBagConstraints();
            activeConst.gridx = 0;
            labelConst.gridx = 1;
            activeConst.gridy   = labelConst.gridy  = i;
            activeConst.anchor  = labelConst.anchor = GridBagConstraints.WEST;
            labelConst.weightx = 1.0;
            activeConst.weightx = 0.0;
            labelConst.insets = new Insets(2, 3, 2, 5);
            activeConst.insets = new Insets(0, -3, 0, 0);
             
            JLabel label = new JLabel(dataInput.getLabel());
            label.setForeground(Color.ORANGE);
            this.add(label, labelConst);
            // TODO: active
            DataPin active = new InputPin(dataInput);
            this.add(active, activeConst);
        }
        
        i = 0;
        for (DataOutput dataOutput : outputs)
        {
            i++;
            GridBagConstraints activeConst = new GridBagConstraints();
            GridBagConstraints labelConst = new GridBagConstraints();
            labelConst.gridx  = 2;
            activeConst.gridx = 3;
            activeConst.gridy   = labelConst.gridy  = i;
            activeConst.anchor  = labelConst.anchor = GridBagConstraints.EAST;
            labelConst.weightx = 1.0;
            activeConst.weightx = 0.0;
            labelConst.insets = new Insets(2, 5, 2, 3);
            activeConst.insets = new Insets(0, 0, 0, -3);
            
            JLabel label = new JLabel(dataOutput.getLabel());
            label.setForeground(Color.ORANGE);
            this.add(label, labelConst);
            // TODO: active
            DataPin active = new OutputPin(dataOutput);
            this.add(active, activeConst);
            
        }
        
        Dimension size = this.getPreferredSize();
        size.height += 4;
        size.width += 6;
        this.setSize(size);
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        Point currentLoc = this.getLocation();
        Point deltaLoc = new Point(
                e.getX() - dragPos.x,
                e.getY() - dragPos.y);
        
        this.setLocation(currentLoc.x + deltaLoc.x, currentLoc.y + deltaLoc.y);
        
    }
    
    private static int topmost = 0;
    
    @Override
    public void mousePressed(MouseEvent e)
    {
        dragPos = e.getPoint();
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {}    
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void onNodeReady(NodeEvent e)
    {
    }

    @Override
    public void onNodeInvalidated(NodeEvent e)
    {
    }

    @Override
    public void onNodeDestroyed(NodeEvent e)
    {
        if(e.source == linkedNode)
        {
            linkedNode = null;
            this.getParent().remove(this);
            this.getParent().repaint();
        }        
    }
    
}
