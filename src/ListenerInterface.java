import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public interface ListenerInterface {
    public void mousePressed(MouseEvent me);
    public void mouseReleased(MouseEvent me);
    public void mouseClicked(MouseEvent me);
    public void mouseMoved(MouseEvent me);
    public void mouseDragged(MouseEvent me);

    public void keyPressed(KeyEvent ke);

    public void mouseWheelMoved(MouseWheelEvent me);
}