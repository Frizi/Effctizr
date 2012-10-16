package effctizr;

/**
 *
 * @author frizi
 */
public interface NodeObserver {
    public void onNodeReady(NodeEvent e);
    public void onNodeInvalidated(NodeEvent e);
    public void onNodeDestroyed(NodeEvent e);
}
