import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ScoreCardDisplay extends JPanel {
	private int dispWidth, dispHeight;
	
    private String BG_FILE = "Music-Scorecard-128x640-1.jpg";
	private Image imageBG = null;
	
	public Feedback mainCallback = null;
	public int scores[] = new int[8];
	
	ScoreCardDisplay(int width, int height) {
		// Display size
		dispWidth = width;
		dispHeight = height;
		
        // Display background
        try {
            File imgFile = new File(BG_FILE);
            imageBG = ImageIO.read(imgFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

		setBackground(Color.BLACK); 
        setPreferredSize(new Dimension(dispWidth, dispHeight));
        //setMaximumSize(new Dimension(dispWidth, dispHeight));
        //setMinimumSize(new Dimension(dispWidth, dispHeight));
	}

	public void RegisterFeedback(Feedback callback) {
		mainCallback = callback;
	}

	public void Init() {
		// Initial shapes
		for (int i = 0; i < scores.length; i ++) {
			scores[i] = 0; 
		}
	}
	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    	// Draw the background image to Component.
    	if (imageBG != null) {
    		g.drawImage(imageBG, 0, 0, null);
    	}
    	else {
    		g.setColor(Color.BLACK);
    		g.fillRect(0, 0, dispWidth, dispHeight);
    	}
        
    	// Title
        g.setColor(Color.RED);
	    g.setFont(new Font("Helvetica", Font.PLAIN, 18));
	    g.drawString("Score Card", 16, 32);

	    for (int type = 0; type < scores.length; type ++) {
			switch(type)
			{
			case 0:	//case RECT_FILL:
		        g.setColor(Color.WHITE);
			    g.setFont(new Font("Courier", Font.PLAIN, 14));
			    g.drawString("" + scores[type], (dispWidth - 64) / 2, 100);
			    
		        g.setColor(Color.GREEN);
				g.fillRect((dispWidth - 64) / 2, 102, 64, 64);
				break;
			case 1:	//case RECT:
		        g.setColor(Color.WHITE);
			    g.setFont(new Font("Courier", Font.PLAIN, 14));
			    g.drawString("" + scores[type], (dispWidth - 64) / 2, 200);
			    
		        g.setColor(Color.GREEN);
				g.drawRect((dispWidth - 64) / 2, 202, 64, 64);
				break;
			case 2:	//case CYCLE:
		        g.setColor(Color.WHITE);
			    g.setFont(new Font("Courier", Font.PLAIN, 14));
			    g.drawString("" + scores[type], (dispWidth - 64) / 2, 300);
			    
				g.setColor(Color.YELLOW);
				g.drawOval((dispWidth - 64) / 2, 302, 64, 64);
				break;
			case 3:	//case CYCLE_FILL:
		        g.setColor(Color.WHITE);
			    g.setFont(new Font("Courier", Font.PLAIN, 14));
			    g.drawString("" + scores[type], (dispWidth - 64) / 2, 400);
			    
				g.setColor(Color.YELLOW);
				g.fillOval((dispWidth - 64) / 2, 402, 64, 64);
				break;
			}
	    }
    }
    
    void UpdateScore(int type, int score) {
    	scores[type] += score;
    	
    	repaint();
    }
}
