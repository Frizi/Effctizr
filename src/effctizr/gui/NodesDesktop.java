/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package effctizr.gui;

import effctizr.Node;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;

/**
 *
 * @author frizi
 */
public class NodesDesktop extends JDesktopPane implements MouseListener, MouseMotionListener, MouseWheelListener
{
    protected NodePanelMouseListener mouseListener;
    protected JPanel selectionBox;
    protected Point selectionOrigin;
    protected Set<WeakReference<NodePanel>> nodePanels;
    
    public NodesDesktop()
    {
        mouseListener = new NodePanelMouseListener(this);
        nodePanels = new HashSet<>();
        selectionBox = new JPanel();
        selectionBox.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
        selectionBox.setBackground(new Color(194, 216, 222, 127));
        this.add(selectionBox);
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void addNode(Node node)
    {
        NodePanel nodePanel = new NodePanel(node);                
        this.add(nodePanel);
        nodePanels.add(new WeakReference<>(nodePanel));
        nodePanel.addMouseListener(mouseListener);
        nodePanel.addMouseMotionListener(mouseListener);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseListener.blurAll();
        selectionOrigin = e.getPoint();
        // prepare to box selection
    }

    @Override
    public void mouseDragged(MouseEvent e) {        
        // start/update box selection
        if(!selectionBox.isVisible())
        {
            selectionBox.setVisible(true);
            this.moveToFront(selectionBox);
        }
        int width  = Math.abs(e.getX() - selectionOrigin.x);
        int height = Math.abs(e.getY() - selectionOrigin.y);
        int x = Math.min(e.getX(), selectionOrigin.x);
        int y = Math.min(e.getY(), selectionOrigin.y);
        selectionBox.setBounds(x, y, width, height);
        updateSelection(selectionBox.getBounds());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // end box selection
        if(selectionBox.isVisible())
        {
            updateSelection(selectionBox.getBounds());
            selectionBox.setVisible(false);
        }
    }
    
    protected void updateSelection(Rectangle selectionRect)
    {
        for(WeakReference<NodePanel> nodePanelRef : nodePanels)
        {
            NodePanel nodePanel = nodePanelRef.get();
            if(nodePanel == null)
            {
                nodePanels.remove(nodePanelRef);
                continue;
            }
            Rectangle bound = nodePanel.getBounds();

            if( selectionRect.contains(bound) )
            {
                mouseListener.focusNode(nodePanel, false);
            }
            else
            {
                mouseListener.blurNode(nodePanel, false);
            }
        }
    }
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println(e);
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
}
