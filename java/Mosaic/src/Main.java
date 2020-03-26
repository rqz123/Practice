import java.awt.Graphics;

import javax.swing.JApplet;

public class Main extends JApplet{
	public void init() {
		setSize(500, 300);
		
		Mosaic mosaic = new Mosaic();
		setContentPane(mosaic);
	}
	
    // Overriding paint() method 
    @Override
    public void paint(Graphics g)  
    { 
        g.drawString("Hello World", 20, 20); 
    } 
      
}
