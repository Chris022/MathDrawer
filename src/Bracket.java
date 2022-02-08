
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.Font;
import java.awt.*;


public class Bracket extends Symbol {

    Symbol inside;

    public Bracket(Symbol s){
        inside = s;
    }

    @Override
    public void render(Graphics2D g) {
       int height = inside.getHeight(g);
       g.setFont(new Font("Anonymous Pro", Font.PLAIN,pixelsToFont(height)));
       g.translate(0,height/2);
       g.drawString("(",0,0);
       g.translate(g.getFontMetrics().getStringBounds("(", g).getWidth(),-inside.getSpaceDown(g));
       inside.render(g);
       g.translate(0,inside.getSpaceDown(g));
       g.setFont(new Font("Anonymous Pro", Font.PLAIN,pixelsToFont(height)));
       g.drawString(")",0,0);
       g.translate(g.getFontMetrics().getStringBounds(")", g).getWidth(),-height/2);
    }

    public int pixelsToFont(int height){
        return (int) (height * Toolkit.getDefaultToolkit().getScreenResolution() / 72.0);
    }

    @Override
    public void setSize(int size) {
        inside.setSize(size);
    }

    @Override
    public int getWidth(Graphics2D g) {
        g.setFont(new Font("Anonymous Pro", Font.PLAIN, getHeight(g)));
        int a = (int) g.getFontMetrics().getStringBounds("(", g).getWidth();
        int b = inside.getWidth(g);
        g.setFont(new Font("ArialUnicodeMS", Font.PLAIN, getHeight(g)));
        int c = (int) g.getFontMetrics().getStringBounds(")", g).getWidth();
        return a + b + c;
    }

    @Override
    public int getHeight(Graphics2D g) {
        g.setFont(new Font("ArialUnicodeMS", Font.PLAIN,pixelsToFont(inside.getHeight(g))));
        return getStringHeight(g, "("); 
    }

    private int getStringHeight(Graphics2D g2, String str)
    {
        FontRenderContext frc = g2.getFontRenderContext();
        GlyphVector gv = g2.getFont().createGlyphVector(frc, str);
        return (int) gv.getPixelBounds(null, 0, 0).getHeight();
    }
    
}