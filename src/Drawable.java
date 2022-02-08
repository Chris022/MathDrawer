

import java.awt.Graphics2D;

public abstract class Drawable {


    private int xPos;
    private int yPos;
    private boolean selected;

    public int getXPos(){
        return xPos;
    }
    public int getYPos(){
        return yPos;
    }
    public void setXPos(int x){
        xPos = x;
    }
    public void setYPos(int y){
        yPos = y;
    }

    public boolean getSelected(){
        return selected;
    }
    public void setSelected(boolean sel){
        selected = sel;
    }


    public abstract void setPositon(int x, int y);

    public abstract Graphics2D draw(Graphics2D g);

    public abstract Boolean checkIfClicked(int x, int y);

    public abstract void onSelect();

    public abstract void onUnselect();

}