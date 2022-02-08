import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class MathExpression extends Drawable implements ListenerInterface {

    private Screen s;
    private Symbol lastValidExpr = new EmptySymbol();
    public String symbolText = "";

    private int width;
    private int height;
    private int size = 20;

    public MathExpression(MouseListener l, Screen s, int x, int y) {
        this.s = s;
        setXPos(x);
        ;
        setYPos(y);
        l.subscribe(this);
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("MathFont.ttf")));
        } catch (IOException | FontFormatException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public Graphics2D draw(Graphics2D g) {
        BufferedImage bufferedImage = new BufferedImage(s.getWidth(), s.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
        g2.setColor(Color.BLACK);
        g2.translate(getXPos(), getYPos());
        if (getSelected()) {
            symbolText = s.getCommandlineText();
        }

        try {
            lastValidExpr = Parser.parseString(symbolText);
        } catch (Exception e) {
        }
        lastValidExpr.setSize(size);
        lastValidExpr.render(g2);
        width = lastValidExpr.getWidth(g2);
        height = lastValidExpr.getHeight(g2);
        g.drawImage(bufferedImage, null, 0, 0);
        return g;
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        if (getSelected()) {
            setPositon(me.getX(), me.getY());
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public Boolean checkIfClicked(int x, int y) {
        if (x > getXPos() && x < getXPos() + width) {
            if (y < getYPos() + height / 2 && y > getYPos() - height / 2) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setPositon(int x, int y) {
        setXPos(x - width / 2);
        setYPos(y + height / 2);
    }

    @Override
    public void onSelect() {
        s.setCommandlineText(symbolText);
        s.focusCommandLine();
    }

    @Override
    public void onUnselect() {
        s.requestFocus();
        s.setCommandlineText("");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent me) {
        if(getSelected()){
            if(size+me.getWheelRotation() > 0){
                size += me.getWheelRotation();
            }
        }
    }
    
}