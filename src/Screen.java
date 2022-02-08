

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.util.ArrayList;

public class Screen extends JFrame {

    private static final long serialVersionUID = 1L;

    private Panel drawArea;
    private JTextField textField = new JTextField();

    private ArrayList<Drawable> objects = new ArrayList<Drawable>();

    public Screen(String name,ArrayList<Drawable> objects,MouseListener l) {
        super(name);

        this.objects = objects;
        drawArea = new Panel();
        drawArea.addMouseListener(l);
        drawArea.addMouseMotionListener(l);
        drawArea.addMouseWheelListener(l);
        this.addKeyListener(l);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(500,500);
        this.getContentPane().add(drawArea);
        textField.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
        this.add(textField,BorderLayout.PAGE_START);

        this.setVisible(true);
        this.requestFocus();
    }

    public String getCommandlineText(){
        return textField.getText();
    }
    public void setCommandlineText(String s){
        textField.setText(s);
    }

    private boolean drawThreadRunning = false;
    private Thread drawThread = new Thread(
        ()-> {
                while(drawThreadRunning){
                    Toolkit.getDefaultToolkit().sync();
                    drawArea.repaint();
                    try{Thread.sleep(10);}catch(Exception e){}
                }
            }
    );

    public void startDrawing(){
        drawThreadRunning=true;
        drawThread.start();
    }

    public void focusCommandLine(){
        textField.requestFocus();
    }

    private class Panel extends JPanel{

        private static final long serialVersionUID = 2L;

        public Panel(){
            this.setBackground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D graphics = (Graphics2D) g;
            objects.forEach(o -> {
                o.draw(graphics);
            });
        }
    }
    
}