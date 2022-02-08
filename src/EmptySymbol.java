import java.awt.Graphics2D;

public class EmptySymbol extends Symbol {

    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public void setSize(int size) {
    }

    @Override
    public int getWidth(Graphics2D g) {
        return 0;
    }

    @Override
    public int getHeight(Graphics2D g) {
        return 0;
    }
    
}