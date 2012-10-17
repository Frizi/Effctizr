/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package effctizr.gui;

import effctizr.DataInput;
import java.awt.Color;
import javax.swing.border.MatteBorder;

/**
 *
 * @author frizi
 */
public class InputPin extends DataPin
{
    DataInput input;
    public InputPin(DataInput input)
    {
        super();
        this.setBackground(input.getDefault().getTypeColor());
        this.input = input;
        this.setBorder(new MatteBorder(1, 0, 1, 1, Color.LIGHT_GRAY));
    }
}
