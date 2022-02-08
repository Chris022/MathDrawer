import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.awt.Color;

public class Drawing extends Drawable implements ListenerInterface {

    private MouseListener listener;

    private ArrayList<Integer> xPoints = new ArrayList<Integer>();
    private ArrayList<Integer> yPoints = new ArrayList<Integer>();
    private Color color;
    private int width;

    public Drawing(MouseListener l, Color color, int width) {
        listener = l;
        listener.subscribe(this);
        this.color = color;
        this.width = width;
    }

    @Override
    public Graphics2D draw(Graphics2D g) {
        g.setColor(color);
        for (int i = 1; i < xPoints.size(); i++) {
            g.setStroke(new BasicStroke(width));
            g.drawLine(xPoints.get(i - 1), yPoints.get(i - 1), xPoints.get(i), yPoints.get(i));
        }
        return g;
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        listener.unsubscribe(this);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        xPoints.add(me.getX());
        yPoints.add(me.getY());
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public Boolean checkIfClicked(int x, int y) {
        return false;
    }

    @Override
    public void setPositon(int x, int y) {
    }

    @Override
    public void onSelect() {
    }

    @Override
    public void onUnselect() {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent me) {}
    
}