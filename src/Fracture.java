

import java.awt.Graphics2D;
import java.util.ArrayList;


public class Fracture extends Symbol {

    Symbol top;
    Symbol bot;

    public Fracture(Symbol top, Symbol bot){
        this.top = top;
        this.bot = bot;
    }

    public Fracture(ArrayList<Symbol> args){
        try{
            this.top = args.get(0);
        }catch(IndexOutOfBoundsException e){
            this.top = new EmptySymbol();
        }
        try{
            this.bot = args.get(1);
        }catch(IndexOutOfBoundsException e){
            this.bot = new EmptySymbol();
        }
    }

    @Override
    public void render(Graphics2D g) {
        int y = 0;
        g.translate(0, -top.getSpaceDown(g));
        g.translate(0, -2);
        top.render(g);
        g.translate(-top.getWidth(g), top.getSpaceDown(g));
        g.translate(0, 2);
        g.drawLine(0, 0, getWidth(g), 0);
        g.translate(0, 2);
        g.translate(0, bot.getSpaceUp(g));
        bot.render(g);
        g.translate(-bot.getWidth(g),-bot.getSpaceUp(g)-2);
        g.translate(getWidth(g), 0);
    }

    @Override
    public void setSize(int size) {
        top.setSize(size);
        bot.setSize(size);
    }

    @Override
    public int getSpaceDown(Graphics2D g) {
        return bot.getHeight(g)+2;
    }

    @Override
    public int getSpaceUp(Graphics2D g) {
        return top.getHeight(g)+2;
    }

    @Override
    public int getWidth(Graphics2D g) {
        int a = top.getWidth(g);
        int b = bot.getWidth(g);

        return (int) (a>b?a:b);
    }

    @Override
    public int getHeight(Graphics2D g) {
        return top.getHeight(g)+bot.getHeight(g)+4;
    }

    private int getHigher(Graphics2D g){
        int a = top.getHeight(g);
        int b = bot.getHeight(g);

        return a>b?a:b;
    }
    
}