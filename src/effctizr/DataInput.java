package effctizr;

/**
 *
 * @author frizi
 */
public class DataInput
{
    protected DataType requiredType;
    protected Data referencedData;
    protected Data defaultData;
    protected String label;

    public DataInput(DataType required, String label, Data defaultData)
    {
        this.requiredType = required;
        this.label = label;
        assert defaultData.isTypeOf(required) : "wrong default data type";
        this.defaultData = defaultData;
    }
    
    public String getLabel() { return label; }
    
    public Data getData()
    {
        return referencedData;
    }
    
    public Data getDefault()
    {
        return defaultData;
    }
    
    public void setData(Data set)
    {
        this.referencedData = set;
    }
    
    public boolean matchesType(Data d)
    {
        return d.isTypeOf(requiredType);
    }
    
    public boolean LinkData(Data d)
    {
        if(matchesType(d))
        {
            this.referencedData = d;
            return true;
        }
        return false;
    }
    
    public boolean isOwner(Node node)
    {
        if(referencedData != null)
        {
            return node == referencedData.owner;
        }
        return false;
    }
}
