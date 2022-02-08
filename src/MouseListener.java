

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.event.MouseInputListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MouseListener implements MouseInputListener, KeyListener, MouseWheelListener {

    private ArrayList<ListenerInterface> subscribers = new ArrayList<ListenerInterface>();

    public void subscribe(ListenerInterface d) {
        subscribers.add(d);
    }

    public void unsubscribe(ListenerInterface d) {
        subscribers.remove(d);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < subscribers.size(); i++) {
            subscribers.get(i).mouseClicked(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < subscribers.size(); i++) {
            subscribers.get(i).mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i < subscribers.size(); i++) {
            subscribers.get(i).mouseReleased(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (int i = 0; i < subscribers.size(); i++) {
            subscribers.get(i).mouseDragged(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (int i = 0; i < subscribers.size(); i++) {
            subscribers.get(i).mouseMoved(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (int i = 0; i < subscribers.size(); i++) {
            subscribers.get(i).keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {

    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        for (int i = 0; i < subscribers.size(); i++) {
            subscribers.get(i).mouseWheelMoved(e);
        }
    }

}