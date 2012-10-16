package effctizr;

/**
 *
 * @author frizi
 */
public class DataInput
{
    protected DataType requiredType;
    protected Data referencedData;
    
    public Data getData()
    {
        return referencedData;
    }
    
    public void setData(Data set)
    {
        this.referencedData = set;
    }
    
    public DataInput(DataType required)
    {
        this.requiredType = required;
    }
    
    public boolean LinkData(Data d)
    {
        if(d.isTypeOf(requiredType))
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
