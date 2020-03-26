// Java program to illustrate the CardLayout Class 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.JFrame; 
import javax.swing.*; 

interface Callback {
	void Call1();
	void Call2();
}

// class extends JFrame and implements actionlistener 
public class CardLayoutTest extends JFrame implements Callback { 
	Disp1 dsp1;
	Disp2 dsp2;
	
	// Declaration of objects of CardLayout class. 
	CardLayout card; 

	// Declaration of objects 
	// of Container class. 
	Container c; 

	CardLayoutTest() 
	{ 
		// to get the content 
		c = getContentPane(); 

		// Initialization of object "card" 
		// of CardLayout class with 40 
		// horizontal space and 30 vertical space . 
		card = new CardLayout(10,10); 

		// set the layout 
		c.setLayout(card); 

		dsp1 = new Disp1();
		dsp1.call = this;
		
		dsp2 = new Disp2();
		dsp2.call = this;

		c.add("Display 1", dsp1); 
		c.add("Display 2", dsp2); 
		
		//card.show(c, "Display 2");
		dsp2.setFocusable(true);
	} 
	
	public void Call1() {
		System.out.println("Switch from Disp1 to Disp2");
		card.next(c);
	}
	
	public void Call2() {
		System.out.println("Switch from Disp2 to Disp1");
		card.next(c);
	}

	// Main Method 
	public static void main(String[] args) 
	{ 
		
		// Creating Object of CardLayout class. 
		CardLayoutTest cl = new CardLayoutTest(); 

		// Function to set size of JFrame. 
		cl.setSize(400, 400); 

		// Function to set default operation of JFrame. 
		cl.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		// Function to set visibility of JFrame. 
		cl.setVisible(true); 
	} 
} 
