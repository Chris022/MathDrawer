import java.awt.Graphics2D;
import java.util.ArrayList;

public class Power extends Symbol {

    Symbol exponent;
    Symbol base;

    public Power(Symbol base,Symbol exponent){
        this.base = base;
        this.exponent = exponent;
    }
    public Power(ArrayList<Symbol> args){
        try{
            this.base = args.get(0);
        }catch(IndexOutOfBoundsException e){
            this.base = new EmptySymbol();
        }
        try{
            this.exponent = args.get(1);
        }catch(IndexOutOfBoundsException e){
            this.exponent = new EmptySymbol();
        }
    }


    @Override
    public void render(Graphics2D g) {
        int yTrans = 0;
        base.render(g);
        if(exponent instanceof Power){
            yTrans = (base.getHeight(g)/2+((Power)exponent).getBaseHeight(g)/2);
        }else{
            yTrans = (base.getHeight(g)/2+exponent.getHeight(g)/2);   
        }
        g.translate(0,-yTrans);
        exponent.render(g);
        g.translate(0, yTrans);
    }
    @Override
    public void setSize(int size) {
        base.setSize(size);
        exponent.setSize(size);
    }

    @Override
    public int getSpaceDown(Graphics2D g) {
        return base.getHeight(g)/2;
    }

    @Override
    public int getSpaceUp(Graphics2D g) {
        return exponent.getHeight(g)+base.getHeight(g)/2;
    }

    public int getBaseHeight(Graphics2D g){
        return base.getHeight(g);
    }

    @Override
    public int getWidth(Graphics2D g) {
        return base.getWidth(g)+exponent.getWidth(g);
    }

    @Override
    public int getHeight(Graphics2D g) {
        return base.getHeight(g)+exponent.getHeight(g);
    }
    
}