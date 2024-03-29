package effctizr.gui;

import effctizr.DataOutput;
import java.awt.Color;
import javax.swing.border.MatteBorder;

/**
 *
 * @author frizi
 */
public final class OutputPin extends DataPin
{
    DataOutput output;
    public OutputPin(DataOutput output)
    {
        super();
        this.setBackground(output.getData().getTypeColor());
        this.output = output;
        this.blur();
    }

    @Override
    public void focus()
    {
        this.setBorder(new MatteBorder(1, 1, 1, 0, Color.ORANGE));
    }

    @Override
    public void blur()
    {
        this.setBorder(new MatteBorder(1, 1, 1, 0, Color.LIGHT_GRAY));
    }
}
