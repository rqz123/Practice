import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import Disp1.KeyHandler;

public class Disp2 extends JPanel {
	public Callback call;
	
	Disp2() {
        addKeyListener(new KeyHandler());

		addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				call.Call2();
			} 
		}); 
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
		g.setColor(Color.RED);
		g.fillOval(0, 0, 300, 300);
    }

	private class KeyHandler extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
        	System.out.println(e);
        	if (e.getKeyCode() == KeyEvent.VK_M) {
        		call.Call2();
        	}
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	System.out.println(e);
        }
	}
}
