package effctizr.gui;

import javax.swing.JPanel;

/**
 *
 * @author frizi
 */
public abstract class DataPin extends JPanel
{

    public DataPin()
    {
        this.setSize(16, 16);
    }
    public abstract void focus();
    public abstract void blur();
}
