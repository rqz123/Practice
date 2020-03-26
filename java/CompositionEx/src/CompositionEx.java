import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Surface extends JPanel {

    private final int rules[] = {
		AlphaComposite.CLEAR,
		AlphaComposite.DST,
		AlphaComposite.DST_ATOP,
		AlphaComposite.DST_IN,
		AlphaComposite.DST_OUT,
		AlphaComposite.SRC,
		AlphaComposite.SRC_ATOP,
		AlphaComposite.SRC_IN,
		AlphaComposite.SRC_OUT,
		AlphaComposite.SRC_OVER
    };
    
    private BufferedImage dispBuffer = null;
    private float alpha = 1.0f;
	private Image imageBG = null;
    
    Surface(int width, int height) {
    	setBackground(Color.BLACK);
		dispBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        try {
            File imgFile = new File("cow.jpg");
            imageBG = ImageIO.read(imgFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

		Timer timer = new Timer(300, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	alpha = alpha - 0.1f;
		    	if (alpha < 0)
		    		alpha = 1.0f;
		    	System.out.println(alpha);
		        doDrawing(dispBuffer.getGraphics());
		    }
		});
		timer.start();
    }
    
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();

        for (int x = 0, y = 0, i = 0; i < rules.length; x += 200, i++) {

            BufferedImage buffImg = new BufferedImage(200, 200,
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D gbi = buffImg.createGraphics();
            //Graphics gb = buffImg.getGraphics();
            //Graphics2D gbi = (Graphics2D)gb.create();

            gbi.setPaint(Color.YELLOW);
            gbi.fillOval(0, 0, 200, 200);
            
            gbi.setComposite(AlphaComposite.getInstance(rules[i], alpha));

            //gbi.drawImage(imageBG, 0, 0, null);
            gbi.setPaint(Color.GREEN);
            gbi.fillRect(0, 0, 200, 200);

            AlphaComposite ac = AlphaComposite.getInstance(rules[5], 1.0f);
            g2d.setComposite(ac);
            
            g2d.drawImage(buffImg, x, y, null);
            
            gbi.dispose();
            buffImg = null;
        }
        
        g2d.dispose();
        
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //Graphics2D g2d = (Graphics2D) g.create();
        //g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f));
        g.drawImage(dispBuffer, 0, 0, this);
    }
}

public class CompositionEx extends JFrame {

    public CompositionEx() {

        add(new Surface(200*10, 250));

        setTitle("Composition");
        setSize(200*10, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                CompositionEx ex = new CompositionEx();
                ex.setVisible(true);
            }
        });
    }
}

// https://stackoverflow.com/questions/14097386/how-to-make-drawn-images-transparent-in-java

