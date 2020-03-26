/*
Joseph Zhang
DrawShapes.java
2/11/19
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;

import javax.swing.*;
public class DrawShapes extends JFrame
{      
	int dispWidth, dispHeight;
	int mainMenuSize;
	
  public DrawShapes()
  {  
    super ("DrawShapes");//This needs to be in the constructor
    
    dispWidth = 800;
    dispHeight = 600;
    
    mainMenuSize = dispWidth / 4;
    
    setSize(dispWidth, dispHeight);    
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);   
    
    setLocation(200,100);
    setResizable(false);
    
    Panel pan = new Panel();   
    setContentPane(pan);  
    setVisible(true);   
  } 
  
  public static void main (String [] args)   
  {  
    DrawShapes JoeBobKim = new DrawShapes(); //needed to run constructor
  }

  	class Panel extends JPanel implements MouseMotionListener
	{  
		MainMenu mainMenu;
		SubMenu subMenu1, subMenu2, subMenu3, subMenu4;
		
		public Panel()
		{
			mainMenu = new MainMenu((dispWidth - mainMenuSize) / 2, (dispHeight - mainMenuSize) / 2, mainMenuSize, mainMenuSize);
		    subMenu1 = new SubMenu((dispWidth - mainMenuSize) / 2, (dispHeight - mainMenuSize) / 2, mainMenuSize / 2, mainMenuSize / 2, 1);
		    subMenu2 = new SubMenu((dispWidth - mainMenuSize) / 2, (dispHeight - mainMenuSize) / 2 + mainMenuSize / 2, mainMenuSize / 2, mainMenuSize / 2, 2);
		    subMenu3 = new SubMenu((dispWidth - mainMenuSize) / 2 + mainMenuSize / 2, (dispHeight - mainMenuSize) / 2 + mainMenuSize / 2, mainMenuSize / 2, mainMenuSize / 2, 3);
		    subMenu4 = new SubMenu((dispWidth - mainMenuSize) / 2 + mainMenuSize / 2, (dispHeight - mainMenuSize) / 2, mainMenuSize / 2, mainMenuSize / 2, 4);

		    addMouseMotionListener(this);
		    
			Timer flashing = new Timer(100, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mainMenu.Shape();
					
					subMenu1.Move();
					subMenu2.Move();
					subMenu3.Move();
					subMenu4.Move();
					
					repaint();
				}
			});
			flashing.start();
		  
			setBackground(Color.BLACK);
		}
		
		public void mouseMoved(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			
			if (mainMenu.IsInside(x, y))
				mainMenu.Active(true);
			else
				mainMenu.Active(false);
			
			if (subMenu1.IsInside(x, y)) {
				if (subMenu1.IsClose()) {
					subMenu1.Open();
					subMenu2.Close();
					subMenu3.Close();
					subMenu4.Close();
				}
				else if (subMenu1.IsOpen())
					subMenu1.Close();
			}
			else if (subMenu2.IsInside(x, y)) {
			     if (subMenu2.IsClose()) {
					subMenu1.Close();
					subMenu2.Open();
					subMenu3.Close();
					subMenu4.Close();
			     }
			      else if (subMenu2.IsOpen())
			    	  subMenu2.Close();
			}
			else if (subMenu3.IsInside(x, y)) {
			      if (subMenu3.IsClose()) {
			    	  subMenu1.Close();
			    	  subMenu2.Close();
			    	  subMenu3.Open();
			    	  subMenu4.Close();
			      }
			      else if (subMenu3.IsOpen())
			    	  subMenu3.Close();
			}
			else if (subMenu4.IsInside(x, y)) {
			      if (subMenu4.IsClose()) {
			    	  subMenu1.Close();
			    	  subMenu2.Close();
			    	  subMenu3.Close();
			    	  subMenu4.Open();
			      }
			      else if (subMenu4.IsOpen())
			    	  subMenu4.Close();
			}
		}
		
		public void mouseDragged(MouseEvent e) {
		}
	  
	  public class MainMenu {
		  private int loop = -1;
		  private int shape = 0;
		  private boolean bActive = false;
		  
		  public int [] shape_normal = new int[] {-2, -4, -6, -2, 2, 0};
		  public int [] shape_active = new int[] {-4, -8, -16, -6, 2, 6, 2, 0};
		  
		  public int x, y, width, height;
		  
		  MainMenu(int x, int y, int width, int height) {
			this.x = x; 
			this.y = y;
			this.width = width;
			this.height = height;
		  }
		  
		  public void Draw(Graphics g) {
		      g.setColor(Color.RED);
		      g.fillOval(x - shape / 2, y - shape / 2, width + shape, height + shape);
		  }
		  
		  public void Shape() {
			  if (bActive) {
				  loop = (loop + 1) % shape_active.length; 
				  shape = shape_active[loop];
			  }
			  else {
				  loop = (loop + 1) % shape_normal.length; 
				  shape = shape_normal[loop];
			  }
		  }
		  
		  public void Active(boolean bActive) {
			  this.bActive = bActive;
		  }
		  
		  public boolean IsInside(int x, int y) {
			  if (x >= this.x && x < this.x + this.width &&
				  y >= this.y && y < this.y + this.height) {
				  return true;
			  }
			  
			  return false;
		  }
	  }
	  
	  public class SubMenu {
		  private int xo, yo;
		  private int xd, yd;
		  private final int step = 8;
		  
		  private boolean bRunning = false;
		  private boolean bOpen = false;

		  public int x, y, width, height;
		  public int direction;
		  
		  SubMenu(int x, int y, int width, int height, int direction) {
			  this.x = x; 
			  this.y = y;
			  this.width = width;
			  this.height = height;
			  this.direction = direction;
			  
			  switch (this.direction) {
			  case 1: xo = x - step * 15; yo = y - step * 15; break;
			  case 2: xo = x - step * 15; yo = y + step * 15; break;
			  case 3: xo = x + step * 15; yo = y + step * 15; break;
			  case 4: xo = x + step * 15; yo = y - step * 15; break;
			  default: xo = x; yo = y; break;
			  }
		  }

		  public void Draw(Graphics g) {
			  switch (this.direction) {
			  case 1: g.setColor(Color.YELLOW); break;
			  case 2: g.setColor(Color.CYAN); break;
			  case 3: g.setColor(Color.GREEN); break;
			  case 4: g.setColor(Color.BLUE); break;
			  default: g.setColor(Color.DARK_GRAY); break;
			  }
		      g.fillOval(x + xd, y + yd, width, height);
		  }
		  
		  public void Open() {
			  if (!bOpen) {
				  bOpen = true;
				  bRunning = true;
			  }
		  }
		  
		  public void Close() {
			  if (bOpen) {
				  bOpen = false;
				  bRunning = true;
			  }
		  }
		  
		  public boolean IsOpen() {
			  if (bOpen && !bRunning)
				  return true;
			  
			  return false;
		  }
		  
		  public boolean IsClose() {
			  if (!bOpen && !bRunning)
				  return true;
			  
			  return false;
		  }
		  
		  public boolean IsInside(int x, int y) {
			  if (!bRunning) {
				  if (x >= this.x + xd && x < this.x + xd + width &&
					  y >= this.y + yd  && y < this.y + yd + height) {
					  return true;
				  }
			  }
			  
			  return false;
		  }
		  
		  public void Move() {
			  if (bRunning && bOpen)
				  switch (direction) {
				  case 1: 
					  xd -= step; yd -= step;
					  if (x + xd < xo || y + yd < yo) {
						  xd = xo - x; yd = yo - y;
						  bRunning = false;
					  }
					  break;
				  case 2: 
					  xd -= step; yd += step;
					  if (x + xd < xo || y + yd > yo) {
						  xd = xo - x; yd = yo - y;
						  bRunning = false;
					  }
					  break;
				  case 3: 
					  xd += step; yd += step; 
					  if (x + xd > xo || y + yd > yo) {
						  xd = xo - x; yd = yo - y;
						  bRunning = false;
					  }
					  break;
				  case 4: 
					  xd += step; yd -= step; 
					  if (x + xd > xo || y + yd < yo) {
						  xd = xo - x; yd = yo - y;
						  bRunning = false;
					  }
					  break;
				  default:
					  xd = 0; xd = 0;
					  bRunning = false;
					  break;
				  }
			  else if (bRunning && !bOpen) {
				  switch (direction) {
				  case 1: 
					  xd += step; yd += step;
					  if (x + xd > x || y + yd > y) {
						  xd = 0; yd = 0;
						  bRunning = false;
					  }
					  break;
				  case 2: 
					  xd += step; yd -= step; 
					  if (x + xd > x || y + yd < y) {
						  xd = 0; yd = 0;
						  bRunning = false;
					  }
					  break;
				  case 3: 
					  xd -= step; yd -= step; 
					  if (x + xd < x || y + yd < y) {
						  xd = 0; yd = 0;
						  bRunning = false;
					  }
					  break;
				  case 4: 
					  xd -= step; yd += step; 
					  if (x + xd < x || y + yd > y) {
						  xd = 0; yd = 0;
						  bRunning = false;
					  }
					  break;
				  default:
					  xd = 0; xd = 0;
					  bRunning = false;
					  break;
				  }
			  }
		  }
	  }
	  
	  public void paintComponent(Graphics gr)
	  {  
		  super.paintComponent(gr);
	      Graphics2D g = (Graphics2D) gr;
	      
	      // Draw menu area
	      g.setColor(Color.GRAY);
	      g.drawLine(dispWidth / 2, dispHeight / 10, dispWidth / 2, dispHeight / 10 * 9);
	      g.drawLine(dispWidth / 10, dispHeight / 2, dispWidth / 10 * 9, dispHeight / 2);
	      g.drawRect((dispWidth - mainMenuSize) / 2, (dispHeight - mainMenuSize) / 2, mainMenuSize, mainMenuSize);
	      
	      // Draw sub menu circles
	      subMenu1.Draw(g); 
	      subMenu2.Draw(g);
	      subMenu3.Draw(g);
	      subMenu4.Draw(g);
	      
	      // Draw main menu circle
	      mainMenu.Draw(g);
	      
	 /*   
		    g.setColor(Color.BLACK);
		    g.setFont(new Font("Sanserif", Font.PLAIN, 20));
		    g.drawString("DrawShapes.java", 0, 20);
		    
	      g.fillArc(20,20, 80,120,60,60);
	      g.drawLine(60,80,60,120);
	      g.drawRect(40,120,40,60);
	      for (int y=160;y<=170;y+=10)
	      {
	    	  for(int x=40;x<=70;x+=10)
	    	  {
	    	
	    		  g.drawOval(x,y,10,10);
	    	  }
	      }
	      
	      g.drawRect(5,10,155,50);
	      g.drawLine(0,0,160,0);
	      g.fillArc(11,11,48,48,45,270);
	      for (int y=30; y<50; y+=25)
	      {
	    	  for (int x=70;x<=130;x+=30)
	    	  {
	    		  g.fillOval(x,y,20,20);
	    	  }
	      }
	      
	      g.fillArc(100,110,80,40,330,240);
	      g.drawLine(140,130,140,150);
	      g.fillRect(138,150,4,10);
	      for (int y=170;y<=180;y+=10)
	      {
	    	  for (int x=80;x<=200;x+=100)
	    	  {
	    		  g.drawOval(x,y,20,4);
	    	  }
	      }
	      
	      g.drawLine(150,150,340,150);
	      g.fillOval(200,200,100,50);
	      g.fillArc(220,150,150,150,330,60);
	      for (int y=150;y<=190;y+=20)
	      {
	    	  for (int x=200;x<210;x+=20)
	    	  {
	    		  g.fillOval(x,y,10,10);
	    	  }
	      }
	      
	      g.drawOval(175,175,50,50);
	      g.fillArc(172,85,56,100,240,60);
	      g.fillRect(162,175,76,10);
	      for (int y=245;y<=265;y+=10)
	      {
	    	  for (int x=190;x<200;x+=20)
	    	  {
	    		  g.drawLine(x,y,x+20,y);
	    	  }
	      }
	 */
	 /*
		    for(int i = 0; i<600;i+=25) 
		    {
		      g.setColor(Color.BLACK);    //lines will be black
		      g.drawLine(i, 0, i, 600);   //draws vertical lines
		      g.drawLine(0, i, 600, i);   //draws horizontal lines
		    }
			//DrawShapes.java
		    g.setColor(Color.BLACK);
		    g.setFont(new Font("Sanserif", Font.PLAIN, 20));
		    g.drawString("DrawShapes.java", 26,246);
		      
		    //Circle1
		    g.setColor(Color.RED);
		    g.fillOval(125, 75, 75, 75);
		    
		    //Rectangle
		    g.setColor(Color.RED);
		    g.fillRect(275, 75, 175, 100);
		    
		    //Ova1
		    g.setColor(Color.RED);
		    g.fillOval(150, 400, 175, 125);
		    
		    //setStroke
		    ((Graphics2D)g).setStroke(new BasicStroke(5));
		    //Arc1
		    
		    g.drawArc(375, 400, 150, 100, 270, 180);
		    
		    //write name "Jos"
		    //J
		    g.drawLine(200, 225, 250, 225);
		    g.drawLine(225, 225, 225, 350);
		    g.drawLine(175, 350, 225, 350);
		    g.drawLine(150, 325, 175, 350);
		   
			//O
		    g.drawLine(300, 250, 325, 250);
		    g.drawLine(325, 250, 350, 275);
		    g.drawLine(350, 275, 350, 325);
		    g.drawLine(325, 350, 350, 325);
		    g.drawLine(300, 350, 325, 350);
		    g.drawLine(275, 325, 300, 350);
		    g.drawLine(275, 275, 275, 325);
		    g.drawLine(275, 275, 300, 250);
		    
		    //S
		    g.drawLine(400, 250, 475, 250);
			g.drawLine(400, 250, 400, 300);
		    g.drawLine(400, 300, 475, 300);
		    g.drawLine(475, 300, 475, 350);
		    g.drawLine(400, 350, 475, 350);
		    
		    
		    //FillRestStroke
		    Color c = new Color(190, 25, 25);
		    g.setColor(c);
		    g.drawOval(125, 75, 75, 75);
		    g.drawRect(275, 75, 175, 100);
		    
		    //Start
		    g.setColor(Color.BLUE);
		    g.fill(createStar(300, 300, 140, 280, 5, 0));
		
		    g.setColor(Color.RED);
		    g.fill(createStar(300, 300, 100, 200, 5, 30));
	*/
	  }
	  
	  private Shape createDefaultStar(double radius, double centerX, double centerY)
	  {
		  return createStar(centerX, centerY, radius, radius * 2.63, 5, Math.toRadians(-18));
	  }
	  
	  private Shape createStar(double centerX, double centerY, 
			  double innerRadius, double outerRadius, int numRays, double startAngleRad)
	  {
		  Path2D path = new Path2D.Double();
		  double deltaAngleRad = Math.PI / numRays;
		  for (int i = 0; i < numRays * 2; i++)
		  {
			  double angleRad = startAngleRad + i * deltaAngleRad;
			  double ca = Math.cos(angleRad);
			  double sa = Math.sin(angleRad);
			  double relX = ca;
			  double relY = sa;
	        
			  if ((i & 1) == 0)
			  {
				  relX *= outerRadius;
				  relY *= outerRadius;
			  }
			  else
			  {
				  relX *= innerRadius;
				  relY *= innerRadius;
			  }
			  if (i == 0)
			  {
				  path.moveTo(centerX + relX, centerY + relY);
			  }
			  else
			  {
				  path.lineTo(centerX + relX, centerY + relY);
			  }
		  }
		  path.closePath();
		  return path;
	  	}
	}
}