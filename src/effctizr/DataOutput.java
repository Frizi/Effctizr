package effctizr;

/**
 *
 * @author frizi
 */
public class DataOutput
{
    protected Data data;
    protected String label;

    public DataOutput(Data data, String label)
    {
        this.data = data;
        this.label = label;
    }
    public Data getData() { return data; } 
    public String getLabel() { return label; } 
}
