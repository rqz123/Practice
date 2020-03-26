import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Disp1  extends JPanel {
	public Callback call;
	
	Disp1() {
        addKeyListener(new KeyHandler());

        addMouseListener(new MouseAdapter() { 
        	public void mousePressed(MouseEvent me) { 
        		call.Call1();
        	} 
        }); 
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, 300, 300);
		
		grabFocus();
    }

	private class KeyHandler extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
        	System.out.println(e);
        	if (e.getKeyCode() == KeyEvent.VK_G) {
        		call.Call1();
        	}
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	System.out.println(e);
        }
	}
}
