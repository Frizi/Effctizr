/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package effctizr.gui;

import effctizr.DataOutput;
import java.awt.Color;
import javax.swing.border.MatteBorder;

/**
 *
 * @author frizi
 */
public class OutputPin extends DataPin
{
    DataOutput output;
    public OutputPin(DataOutput output)
    {
        super();
        this.setBackground(output.getData().getTypeColor());
        this.output = output;
        this.setBorder(new MatteBorder(1, 1, 1, 0, Color.LIGHT_GRAY));
    }
}
