import java.awt.*;
import java.awt.geom.Point2D;

import javax.swing.*; import javax.swing.event.*; 

/*
 * https://www3.ntu.edu.sg/home/ehchua/programming/java/J4b_CustomGraphics.html
 */

public class ThreeSliders 
{
	public ThreeSliders()
	{
		layoutPanel go = new layoutPanel();
	}

	public static void main(String [] args)
	{
		ThreeSliders run = new ThreeSliders();
	}
}

class layoutPanel extends JPanel
{
	public JFrame frame;
	public JPanel panelLeft;
	public JPanel panelRight;
	public JLabel redSquareText;
	public JSlider redSquareSlider;
	public JLabel greenDonutText;
	public JSlider greenDonutSlider;
	public JLabel blueHexagonText;
	public JSlider blueHexagonSlider;

	public int squareValue;
	public int donutValue;
	public int hexagonValue;

	public layoutPanel()
	{
		squareValue = 5;
		donutValue = 5;
		hexagonValue = 5;

		frame = new JFrame ("ThreeSliders.java");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);

		GridLayout layout = new GridLayout(1, 2, 10, 10);
		frame.setLayout(layout);

		leftPanelLayout left = new leftPanelLayout();
		left.setLayout(new GridLayout(3, 1, 0, 0));
	
		rightPanelLayout right = new rightPanelLayout();
		right.setLayout(new GridLayout(9, 1, 0, 0));

		frame.getContentPane().add(left);
		frame.getContentPane().add(right);

		frame.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		// this function won't be called. 
		
		System.out.println("repaint layoutPanel");
	
		super.paintComponent(g);
	}

	class leftPanelLayout extends JPanel 
	{
		public Point squarePosition;
		public Point donutPosition;
		public Point hexagonPosition;
		
		public leftPanelLayout()
		{
			squarePosition = new Point(100, 100);
			donutPosition = new Point(100, 100);
			hexagonPosition = new Point(100, 100);
		}

		public void paintComponent(Graphics gr)
		{
			System.out.println("repaint leftPanelLayout");
		
			super.paintComponent(gr);
			Graphics2D g = (Graphics2D) gr;
			
			int width = getWidth();
			int height = getHeight() / 3;
			
			// draw square
			squarePosition.x = (width - 105) / 2;
			squarePosition.y = height / 2;
			 
			g.setColor(Color.RED);
			g.fillRect(squarePosition.x, squarePosition.y, squareValue, squareValue);
			
			// draw donut
			donutPosition.x = (width - 105) / 2;
			donutPosition.y = height / 2 + height;
			
			g.fillRect(donutPosition.x, donutPosition.y, squareValue, squareValue);
			
			// draw hexagon
			hexagonPosition.x = (width - 105) / 2;
			hexagonPosition.y = height / 2 + height * 2;

			g.fillRect(hexagonPosition.x, hexagonPosition.y, squareValue, squareValue);
		}
	}

	class rightPanelLayout extends JPanel
	{
		public rightPanelLayout()
		{
			AllHandler allhandler = new AllHandler();

			redSquareText = new JLabel("Red Square");
			redSquareSlider = new JSlider(5, 105, squareValue);
			redSquareSlider.setMajorTickSpacing(50);
			redSquareSlider.setPaintTicks(true);
			redSquareSlider.setMinorTickSpacing(10);
			redSquareSlider.setPaintLabels(true);
			redSquareSlider.setOrientation(JSlider.HORIZONTAL);
			redSquareSlider.addChangeListener(allhandler);

			greenDonutText = new JLabel("Green Donut");
			greenDonutSlider = new JSlider(5, 105, donutValue);
			greenDonutSlider.setMajorTickSpacing(50);
			greenDonutSlider.setPaintTicks(true);
			greenDonutSlider.setMinorTickSpacing(10);
			greenDonutSlider.setPaintLabels(true);
			greenDonutSlider.setOrientation(JSlider.HORIZONTAL);
			greenDonutSlider.addChangeListener(allhandler);

			blueHexagonText = new JLabel("Blue Hexagon");
			blueHexagonSlider = new JSlider(5, 105, hexagonValue);
			blueHexagonSlider.setMajorTickSpacing(50);
			blueHexagonSlider.setPaintTicks(true);
			blueHexagonSlider.setMinorTickSpacing(10);
			blueHexagonSlider.setPaintLabels(true);
			blueHexagonSlider.setOrientation(JSlider.HORIZONTAL);
			blueHexagonSlider.addChangeListener(allhandler);

			add(new JLabel(""));
			add(redSquareText);
			add(redSquareSlider);
			
			add(new JLabel(""));
			add(greenDonutText);
			add(greenDonutSlider);
			
			add(new JLabel(""));
			add(blueHexagonText);
			add(blueHexagonSlider);
		}
	
		class AllHandler implements ChangeListener 
		{
			public void stateChanged(ChangeEvent e) 
			{
				squareValue = redSquareSlider.getValue();
				donutValue = greenDonutSlider.getValue();
				hexagonValue = blueHexagonSlider.getValue();
		
				frame.repaint();
			}		
		}
		
		public void paintComponent(Graphics g)
		{
			System.out.println("repaint rightPanelLayout");
		
			super.paintComponent(g);
		}
	}
}
