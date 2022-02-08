import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        new Main().main();
    }

    Drawable editable;
    MouseListener listener = new MouseListener();

    ArrayList<Drawable> objects = new ArrayList<Drawable>();
    Screen screen;

    public void main() {
        screen = new Screen("MathBook", objects, listener);

        listener.subscribe(new ShortCutHandler());

        screen.startDrawing();
    }

    private class ShortCutHandler implements ListenerInterface {

        @Override
        public void mousePressed(MouseEvent me) {
            // if Control is pressed, add a new Drawing, otherwise add new MathExpression
            if (me.isControlDown()) {
                if (me.getButton() == MouseEvent.BUTTON3) {
                    objects.add(new Drawing(listener, Color.WHITE, 20));
                } else if (me.getButton() == MouseEvent.BUTTON1) {
                    objects.add(new Drawing(listener, Color.BLACK, 2));
                }

            } else {
                boolean block = false;
                // check if a object was selected
                for (Drawable o : objects) {
                    if (!o.getSelected() && o.checkIfClicked(me.getX(), me.getY())) {
                        o.onSelect();
                        o.setSelected(true);
                    } else if (o.getSelected()) {
                        o.onUnselect();
                        o.setSelected(false);
                        block = true;
                    }
                }
                // if no object is selected create a new one
                if (!block && objects.stream().allMatch(x -> x.getSelected() == false)) {
                    Drawable d = new MathExpression(listener, screen, me.getX(), me.getY());
                    d.setSelected(true);
                    d.onSelect();
                    objects.add(d);
                }
            }

        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseClicked(MouseEvent me) {

        }

        @Override
        public void mouseMoved(MouseEvent me) {
        }

        @Override
        public void mouseDragged(MouseEvent me) {
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            if (ke.isControlDown() && ke.getKeyCode() == KeyEvent.VK_C) {
                objects.clear();
            }
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent me) {}
        
    }
    
}