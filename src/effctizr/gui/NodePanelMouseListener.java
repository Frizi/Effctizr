/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package effctizr.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author frizi
 */
public class NodePanelMouseListener implements MouseListener, MouseMotionListener
{
    Set<NodePanel> selection;
    NodePanel addingToSelection;
    WeakReference<NodesDesktop> desktopRef;
    Point lastGlobalPos;
    
    public NodePanelMouseListener(NodesDesktop desktop)
    {
        this.desktopRef = new WeakReference<>(desktop);
        selection = new HashSet<>();
    }

    public void focusNode(NodePanel panel) { focusNode(panel, false); }
    public void focusNode(NodePanel panel, boolean moveToFront)
    {
        if(moveToFront)
        {
            desktopRef.get().moveToFront(panel);
        }
        selection.add(panel);
        panel.focus();
    }
    
    public void blurNode(NodePanel panel) { blurNode(panel, false); }
    public void blurNode(NodePanel panel, boolean moveToFront)
    {
        if(moveToFront)
        {
            desktopRef.get().moveToFront(panel);
        }
        selection.remove(panel);
        panel.blur();
    }
    
    public void blurAll()
    {
        for (NodePanel nodePanel : selection)
        {
            nodePanel.blur();
        }
        selection.clear();
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(desktopRef.get() == null) { return; }
        NodePanel evtPanel = (NodePanel) e.getComponent();
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            if(e.isShiftDown())
            {
                if(addingToSelection != evtPanel && selection.contains(evtPanel))
                {
                    blurNode(evtPanel, true);
                }
                else
                {
                    focusNode(evtPanel, true);
                }
                addingToSelection = null;
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {
        if(desktopRef.get() == null) { return; }
        
        NodePanel evtPanel = (NodePanel) e.getComponent();
        
        switch(e.getButton())
        {
            case(MouseEvent.BUTTON1):
                lastGlobalPos = e.getLocationOnScreen();
                
                desktopRef.get().moveToFront(evtPanel);

                if(!e.isShiftDown())
                {
                    if(!selection.contains(evtPanel))
                    {
                        blurAll();
                        focusNode(evtPanel, true);
                    }
                }
                else
                {
                    if(selection.contains(evtPanel))
                    {
                    }
                    else
                    {
                        focusNode(evtPanel, true);
                        addingToSelection = evtPanel;
                    }
                }
                
                
                break;
            case(MouseEvent.BUTTON2):                
                break;
            case(MouseEvent.BUTTON3):
                // context menu
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(desktopRef.get() == null) { return; }
        lastGlobalPos = null;
    }
    
    @Override
    public void mouseDragged(MouseEvent e)
    {
        if(desktopRef.get() == null) { return; }
        assert lastGlobalPos != null : "lastGlobalPos is null somehow";
        
        Point curPos = e.getLocationOnScreen();
        Point deltaPos = new Point(curPos.x - lastGlobalPos.x, curPos.y - lastGlobalPos.y);        
        lastGlobalPos = curPos;

        for (NodePanel nodePanel : selection)
        {
            Point loc = nodePanel.getLocation();
            nodePanel.setLocation(loc.x + deltaPos.x, loc.y + deltaPos.y);
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
    
}
