import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class SymbolChain extends Symbol {

    ArrayList<Symbol> symbols = new ArrayList<Symbol>();

    public SymbolChain(Symbol[] symbols){
        this.symbols = new ArrayList<Symbol>(List.of(symbols));
    }

    public SymbolChain(){}

    public void addSymbol(Symbol s){
        symbols.add(s);
    }

    @Override
    public int getSpaceDown(Graphics2D g) {
        int h =0;
        for(Symbol s : symbols){
            if(s.getSpaceDown(g)>h){
                h = s.getSpaceDown(g);
            }
        }
        return h;
    }

    @Override
    public int getSpaceUp(Graphics2D g) {
        int h =0;
        for(Symbol s : symbols){
            if(s.getSpaceUp(g)>h){
                h = s.getSpaceUp(g);
            }
        }
        return h;
    }

    @Override
    public void render(Graphics2D g) {
        for(Symbol s : symbols){
            s.render(g);
        }
    }

    @Override
    public void setSize(int size) {
        for(Symbol s : symbols){
            s.setSize(size);
        }
    }

    @Override
    public int getWidth(Graphics2D g) {
        int w =0;
        for(Symbol s : symbols){
            w+=s.getWidth(g);
        }
        return w;
    }

    @Override
    public int getHeight(Graphics2D g) {
        int h =0;
        for(Symbol s : symbols){
            if(s.getHeight(g)>h){
                h = s.getHeight(g);
            }
        }
        return h;
    }
    
    
}