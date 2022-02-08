

import java.awt.Graphics2D;

public abstract class Symbol {

    public abstract void render(Graphics2D g);
    public abstract int getWidth(Graphics2D g);
    public abstract int getHeight(Graphics2D g);

    public abstract void setSize(int size);

    public int getSpaceUp(Graphics2D g){
        return this.getHeight(g)/2;
    }
    public int getSpaceDown(Graphics2D g){
        return this.getHeight(g)/2;
    }
}