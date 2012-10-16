package effctizr;

/**
 *
 * @author frizi
 */
public class DataInput
{
    protected DataType requiredType;
    protected Data referencedData;
    protected String label;

    public DataInput(DataType required, String label)
    {
        this.requiredType = required;
        this.label = label;
    }
    
    public String getLabel() { return label; }
    
    public Data getData()
    {
        return referencedData;
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
