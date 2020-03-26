import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class calculator extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new calculator();
	}

	public calculator() 
	{
		initComponents();
	}

	class MenuActionListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Selected: " + e.getActionCommand());
			
			if (e.getActionCommand() == "Exit")
			{
				System.exit(0);
			}
		}
	}
	
	private void initComponents() 
	{
		JFrame frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Menu for test
		JMenuBar menuBar = new JMenuBar();
		JMenu menuMain = new JMenu("Menu");
        JMenu subMenu = new JMenu("Sub Menu");  
        MenuActionListener menuAct = new MenuActionListener();
        
        JMenuItem menuItem1=new JMenuItem("Item 1");
        menuItem1.addActionListener(menuAct);
        JMenuItem menuItem2=new JMenuItem("Item 2");  
        menuItem2.addActionListener(menuAct);
        JMenuItem menuItem3=new JMenuItem("Exit");  
        menuItem3.addActionListener(menuAct);
        JMenuItem menuItem4=new JMenuItem("Item 4");  
        menuItem4.addActionListener(menuAct);
        JMenuItem menuItem5=new JMenuItem("Item 5"); 
        menuItem5.addActionListener(menuAct);
        
        menuMain.add(menuItem1); 
        menuMain.add(menuItem2);
        menuMain.addSeparator();
        menuMain.add(menuItem3);

        menuMain.addSeparator();
        
        subMenu.add(menuItem4); 
        subMenu.add(menuItem5); 
        
        menuMain.add(subMenu);  
        menuBar.add(menuMain);  
        
		// Panel for test
		//JPanel panelScreen = new JPanel(new GridLayout(1,3));
		JPanel panelScreen = new JPanel(new FlowLayout());

		JLabel oneLabel = new JLabel("Position 1");
		oneLabel.setVerticalAlignment(SwingConstants.CENTER);
		oneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		oneLabel.setFont(new Font(oneLabel.getFont().getName(), Font.PLAIN, 30));
		panelScreen.add(oneLabel);

		JTextArea text = new JTextArea("This is text area", 5, 30);
		text.setMargin( new Insets(100,100,10,10) );
		panelScreen.add(text);

		JLabel twoLabel = new JLabel("Position 3");
		twoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panelScreen.add(twoLabel);

		// Panel for number
		JPanel panelButtons = new JPanel(new GridLayout(3,3));

		JButton oneButton = new JButton("1");
		panelButtons.add(oneButton);
		
		JButton twoButton = new JButton("2");
		panelButtons.add(twoButton);
		
		JButton threeButton = new JButton("3");
		panelButtons.add(threeButton);
		
		JButton fourButton = new JButton("4");
		panelButtons.add(fourButton);
		
		JButton fiveButton = new JButton("5");
		panelButtons.add(fiveButton);
		
		JButton sixButton = new JButton("6");
		panelButtons.add(sixButton);
		
		JButton sevenButton = new JButton("7");
		panelButtons.add(sevenButton);
		
		JButton eightButton = new JButton("8");
		panelButtons.add(eightButton);
		
		JButton nineButton = new JButton("9");
		panelButtons.add(nineButton);

		frame.setJMenuBar(menuBar);
		//frame.getContentPane().add(panelScreen, BorderLayout.NORTH);
		frame.getContentPane().add(panelScreen, BorderLayout.NORTH);
		frame.getContentPane().add(new JSeparator(), BorderLayout.CENTER);
		frame.getContentPane().add(panelButtons, BorderLayout.SOUTH);
    
		frame.setBounds(50, 50, 500, 500);
		//frame.setResizable(false);
		//frame.pack();
		
		frame.setVisible(true);
	}
}
