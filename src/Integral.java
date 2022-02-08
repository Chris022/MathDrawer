import java.awt.Graphics2D;
import java.util.ArrayList;


public class Integral extends Symbol {

    Symbol base;
    Symbol to;
    Symbol from;

    public Integral(Symbol base,Symbol to, Symbol from){
        this.base = base;
        this.to = to;
        this.from = from;
    }

    public Integral(ArrayList<Symbol> args){
        try{
            this.base = args.get(0);
        }catch(IndexOutOfBoundsException e){
            this.base = new EmptySymbol();
        }
        try{
            this.from = args.get(1);
        }catch(IndexOutOfBoundsException e){
            this.from = new EmptySymbol();
        }
        try{
            this.to = args.get(2);
        }catch(IndexOutOfBoundsException e){
            this.to = new EmptySymbol();
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawLine(0,base.getHeight(g),(int)(base.getWidth(g)*0.4),base.getHeight(g));
        g.translate((int)(base.getWidth(g)*0.4),0);
        g.drawLine(0, -base.getHeight(g), 0, base.getHeight(g));
        g.drawLine(0,-base.getHeight(g),(int)(base.getWidth(g)*0.4),-base.getHeight(g));
        
        g.translate(0,-(base.getHeight(g)+to.getSpaceDown(g)+2));
        to.render(g);
        g.translate(-to.getWidth(g)-(int)(base.getWidth(g)*0.4),to.getSpaceDown(g)+base.getHeight(g)+2);
        g.translate(0, base.getHeight(g)+from.getSpaceUp(g)+6);
        from.render(g);
        g.translate((int)-from.getWidth(g),-(from.getSpaceUp(g)+base.getHeight(g)+6));
        
        g.translate((base.getWidth(g)*0.8), 0);
        base.render(g);
        g.translate(-base.getWidth(g)-(base.getWidth(g)*0.8),0);
        g.translate(getWidth(g), 0);
    }

    public int getLongest(Graphics2D g){
        int toFromWidth = (from.getWidth(g)-base.getWidth(g)*0.4) >= to.getWidth(g) ? (int) (from.getWidth(g)-base.getWidth(g)*0.4) : to.getWidth(g);
        return base.getWidth(g)*1.8 >= toFromWidth ?  (int)(base.getWidth(g)*1.8) : toFromWidth;
    }

    @Override
    public void setSize(int size) {
        base.setSize(size);
        to.setSize(size);
        from.setSize(size);
    }

    @Override
    public int getWidth(Graphics2D g) {
        int w = 0;

        w+=base.getWidth(g)*0.4;
        w+=getLongest(g);
        w+=base.getWidth(g)*0.4;
        return w;
    }

    @Override
    public int getSpaceUp(Graphics2D g){
        return to.getHeight(g)+2 + base.getHeight(g);
    }

    @Override
    public int getSpaceDown(Graphics2D g){
        return from.getHeight(g)+6 + base.getHeight(g);
    }

    @Override
    public int getHeight(Graphics2D g) {
        int h = base.getHeight(g)*2;
        h += to.getHeight(g)+4;
        h += from.getHeight(g)+6;
        return h;
    }
    
}