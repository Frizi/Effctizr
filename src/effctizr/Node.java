/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package effctizr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author frizi
 */
public abstract class Node implements NodeObserver {
    protected boolean ready;
    protected Set<NodeObserver> observers;
    
    // input & outputs
    protected ArrayList<DataInput> inputs;
    protected ArrayList<Data> outputs;
    
    public Node()
    {
        observers = new HashSet<>();
    }
    
    public boolean trySetInput(DataInput input, Data output)
    {
        assert input.isOwner(this) : "cannot set input for other node";
        
        Data oldData = input.getData();
        if(input.LinkData(output))
        {
            if(oldData != null && oldData.owner != this)
            {
                // check if need to unregister observer
                boolean unregister = true;
                for (Iterator<DataInput> it = inputs.iterator(); it.hasNext();)
                {
                    DataInput dataInput = it.next();
                    if(dataInput.getData().owner == oldData.owner)
                    {
                        unregister = true;
                        break;
                    }
                }
                if(unregister)
                {
                    oldData.owner.removeObserver(this);
                }
            }
            
            if(output.owner != this)
            {
                output.owner.addObserver(this);
            }
            return true;
        }
        return false;
    }
    
    /*
     * internal events
     */
    protected void onSelfReady()
    {
        if(!this.ready)
        {
            this.ready = true;
            this.fireReadyEvent();
        }
    }
    
    protected void onSelfInvalidate()
    {
        if(this.ready)
        {
            this.ready = false;
            this.fireInvalidateEvent();
        }
    }
    
    protected void onSelfDestroyed()
    {
        this.ready = false;
        this.fireDestroyedEvent();
    }
    
    /*
     * observable functions
     */
    public void addObserver(NodeObserver obs)
    {
        assert obs != this : "cannot observe self";
        observers.add(obs);
    }
    
    public void removeObserver(NodeObserver obs)
    {
        observers.remove(obs);
    }
    
    /*
     * observable events
     */
    protected void fireReadyEvent()
    {
        for (Iterator<NodeObserver> it = observers.iterator(); it.hasNext();)
        {
            NodeObserver nodeObserver = it.next();
            nodeObserver.onNodeReady(new NodeEvent(this));
        }
    }
    
    protected void fireInvalidateEvent()
    {
        for (Iterator<NodeObserver> it = observers.iterator(); it.hasNext();)
        {
            NodeObserver nodeObserver = it.next();
            nodeObserver.onNodeInvalidated(new NodeEvent(this));
        }
    }
    
    protected void fireDestroyedEvent()
    {
        for (Iterator<NodeObserver> it = observers.iterator(); it.hasNext();)
        {
            NodeObserver nodeObserver = it.next();
            nodeObserver.onNodeDestroyed(new NodeEvent(this));
        }
    }
    
    @Override
    public void onNodeReady(NodeEvent e)
    {
        /*
        // check if all data sources ready
        if (all ready)
        {
            // calculate data
        }
        */
    }

    @Override
    public void onNodeInvalidated(NodeEvent e)
    {
        this.onSelfInvalidate();
    }

    @Override
    public void onNodeDestroyed(NodeEvent e)
    {
        this.onSelfInvalidate();
        
        // clear inputs from source
        for (Iterator<DataInput> it = inputs.iterator(); it.hasNext();)
        {
            DataInput dataInput = it.next();
            if(dataInput.isOwner(e.source))
            {
                dataInput.setData(null);
            }
        }
        // remove source from observables
        e.source.removeObserver(this);
    }
}