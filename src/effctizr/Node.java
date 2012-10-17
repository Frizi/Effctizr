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
    protected ArrayList<Data> defaults;
    protected ArrayList<DataOutput> outputs;
    
    public Node()
    {
        this.ready = false;
        observers = new HashSet<>();
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
    }
    public abstract String getHeader();
    
    // for gui
    public ArrayList<DataInput> getInputList() { return inputs; }
    public ArrayList<DataOutput> getOutputList() { return outputs; }
    
    /**

     * The function that needs to initialize
     * inputs, outputs and defaults.
     */
    protected abstract void initializeInterface();
    
    protected abstract void update();
    protected Data getInput(int inputId)
    {
        assert inputId < inputs.size() : "trying to get inexistent input " + inputId;
        Data input = inputs.get(inputId).getData();
        if(input == null)
        {
            return inputs.get(inputId).getDefault();
        }
        return input;
    }
    
    protected Data getOutput(int outputId)
    {
        assert outputId < inputs.size() : "trying to get inexistent output " + outputId;
        Data output = outputs.get(outputId).getData();
        assert output != null : "output " + outputId + " is null!";
        return output;
    }
    
    public boolean trySetInput(int inputId, Data output)
    {
        DataInput input = inputs.get(inputId);
        Data oldData = input.getData();
        if(input.matchesType(output))
        {
            this.onSelfInvalidate();
            input.LinkData(output);
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
            this.checkAndUpdate();
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
    
    protected void checkAndUpdate()
    {
        boolean allReady = true;
        
        // check if all data sources ready
        for (Iterator<DataInput> it = inputs.iterator(); it.hasNext();)
        {
            Data data = it.next().getData();
            if( data != null && !data.owner.ready )
            {
                allReady = false;
                break;
            }
        }
        
        if (allReady)
        {
            // calculate data
            update();
        }
    }
    
    public void beginDataUpdate()
    {
        this.onSelfInvalidate();
    }
    
    public void endDataUpdate()
    {
        this.checkAndUpdate();
    }
    
    @Override
    public void onNodeReady(NodeEvent e)
    {
        checkAndUpdate();
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