/*
Joseph Zhang
ButtonSlider.java
3/19/19
 */
import java.awt.*;    import javax.swing.*;
import java.awt.event.*; import javax.swing.event.*;
//button1.setPreferredSize(new Dimension(125, 40));

public class ButtonSlider extends JFrame
{ 
	public static void main( String[] args )
	{
		ButtonSlider pL2 = new ButtonSlider();
	}

	public ButtonSlider()
	{
		super("ButtonSlider");

		setSize( 900, 500);    
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);   
		setLocation(80,50);
		setResizable(true);
		
		ButtonSliderP2 pillsPanel2 = new ButtonSliderP2();   
		setContentPane( pillsPanel2 );  // OR frame.getContentPane().add(p_in);
		
		setVisible(true);  
	}
}

class ButtonSliderP2 extends JPanel implements MouseListener, MouseMotionListener 
{ 
	private int xMouse, yMouse;
	private int xloc, yloc;
	private boolean pressed1, pressed2; 
	private boolean times1, times2;
	private JButton button1, button2, button3, button4; 
	private Font font;
	private int counter;
	private int previousValue;
	private JSlider slider;
	private int sliderValue;
	private int hellocounter;
	private int bellycounter;
	private Rectangle exit;

	public ButtonSliderP2() 
	{

		pressed1 = pressed2 = false; 
		font = new Font ("Serif", Font.BOLD, 30);

		setBackground( Color.MAGENTA );
		setLayout( new FlowLayout( FlowLayout.RIGHT, 0, 0) ); 
		//setLayout( new FlowLayout( FlowLayout.LEFT, 50, 50) ); 

		button1 = new JButton("Press my belly.");  // construct button 
		Button1Handler b1handler = new Button1Handler(); // this is so the actionPerformed is dedicated to this button only
		button1.addActionListener( b1handler );   // add listener to button
		add( button1 );  // add button to panel (Pillsbury2)
		button1.setPreferredSize(new Dimension(125 , 40)); 

		button2 = new JButton("Hello"); 
		Button2Handler b2handler = new Button2Handler();
		button2.addActionListener( b2handler ); 
		add( button2 ); 
		button2.setPreferredSize(new Dimension(125 , 40)); 

		button3 = new JButton("Press my belly.");
		Button1Handler b3handler = new Button1Handler();
		button3.addActionListener( b1handler );
		add( button3 );
		button3.setPreferredSize(new Dimension(125 , 40)); 

		button4 = new JButton("Hello"); 
		Button2Handler b4handler = new Button2Handler();
		button4.addActionListener( b2handler );
		add( button4 ); 
		button4.setPreferredSize(new Dimension(125 , 40)); 

		JPanel jp = new JPanel();  
		add ( jp );


		slider = new JSlider (JSlider.HORIZONTAL, 10, 125, 12);  // construct slider bar 
		slider.setMajorTickSpacing(23); // create tick marks on slider every 40 units
		slider.setMinorTickSpacing(8);
		
		slider.setForeground(Color.BLUE);
		slider.setPaintTicks(true);
		slider.setLabelTable( slider.createStandardLabels(23) ); // create labels on tick marks
		slider.setInverted(true);
		slider.setPaintLabels(true);
		SliderHandler sHandler = new SliderHandler();
		add(slider);  // add button to panel 
		slider.addChangeListener( sHandler );   // add listener to slider
		sliderValue = 12;
		addMouseListener(this);
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent (g);
		
		Image image1 = new ImageIcon("PillburyDoughboy1.png").getImage();
	    
		g.setColor(Color.WHITE);
		g.fillRect(850, 428, 50, 50);
		exit = new Rectangle(850, 428, 50, 50);
		
		Font f = new Font("Serif", Font.BOLD, sliderValue);
		g.setFont(f);
		g.drawString ("Move the slider to see",100, 250);
		g.drawString ("the font size change",100, 270 + sliderValue);
		
		g.setColor(Color.BLACK);
		g.drawLine(20,100, 150,100);
		g.fillRect(50,100-sliderValue/2,sliderValue,sliderValue);
	    
		if(pressed1 && times1)
		{
			//System.out.println("times1: " + times1);
			//System.out.println("path2");
			g.setFont( font );
			g.drawString("Stop pressing my belly", 100, 150);  
			pressed1 = false;
			times1 = false;
		}
		else if (pressed1)
		{
			//System.out.println("path1");
			g.setFont( font );
			g.drawString("Hee-Hee", 100, 150); 
			g.drawImage(image1, -100, 270, 310, 200, null);
			pressed1 = false; 
		}

		if(pressed2 && times2)
		{
			g.setFont( font );
			g.drawString("Stop saying Hello", 100, 150);  
			pressed1 = false;
			times2 = false;
		}
		else if (pressed2)
		{
			g.setFont( font );
			g.drawString("You say", 200, 150);  
			pressed2 = false; 
		}
	}

	class Button1Handler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			String command = e.getActionCommand();
			if(command.equals ("Press my belly.") && bellycounter == 5)
			{
				//System.out.println("path2");
				pressed1 = true;   
				times1 = true;
				button1.setText ("reset");
				bellycounter = 0;
			}
			else if ( command.equals ("Press my belly.") ) 
			{   
				//System.out.println("path1");
				pressed1 = true;   
				button1.setText ("reset");
				bellycounter++;
			} 
			else  
				button1.setText("Press my belly."); 
			repaint();
		}
	} // end class Button1Handler 

	class Button2Handler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			String command2 = e.getActionCommand();
			if ( command2.equals ("Hello") && hellocounter == 4) 
			{   
				pressed2 = true;   
				times2 = true;
				button2.setText ("Goodbye");  
				hellocounter=0;
			}
			else if ( command2.equals ("Hello") ) 
			{   
				pressed2 = true;   
				button2.setText ("Goodbye");  
				hellocounter++;
			}  
			else  
				button2.setText("Hello"); 
			repaint();
		}
	} // end class Button2Handler 

	class SliderHandler implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{    //  event handler for JSliderBar (stateChanged)
			sliderValue = slider.getValue();
			repaint();
		} 
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		xMouse = e.getX();
		yMouse = e.getY();
		if(exit.contains(e.getX(), e.getY()))
		{
			System.exit(0);
		}
	}
}
