import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;

public class AsciiSymbol extends Symbol{
    private char sym;
    private int size;
    public AsciiSymbol(char ke){
        sym = ke;
    }

    public void setSymbol(char c){
        sym = c;
    }

    public char getSymbol(){
        return sym;
    }

    @Override
    public void render(Graphics2D g){
        g.setFont(new Font("Anonymous Pro", Font.PLAIN, size));
        g.translate(0,getHeight(g)/2);
        g.drawString(sym+"", 0, 0);
        g.translate(g.getFontMetrics().getStringBounds(sym+"",g).getWidth(), 0);
        g.translate(0,-getHeight(g)/2);
    }

    @Override
    public void setSize(int size){
        this.size = size;
    }
    @Override
    public int getWidth(Graphics2D g){
        g.setFont(new Font("Anonymous Pro", Font.PLAIN, size));
        return (int)g.getFontMetrics().getStringBounds(sym+"",g).getWidth();
    }
    @Override
    public int getHeight(Graphics2D g){
        g.setFont(new Font("Anonymous Pro", Font.PLAIN, size));
        return getStringHeight(g,sym+"");
    }

    private int getStringHeight(Graphics2D g2, String str)
    {
        FontRenderContext frc = g2.getFontRenderContext();
        GlyphVector gv = g2.getFont().createGlyphVector(frc, str);
        return (int) gv.getPixelBounds(null, 0, 0).getHeight();
    }

    
}