package package1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ButtonPanel extends JPanel{
	//==============================================================================================
	//==== Data
	
	DataBox dbox;
	
	// main window
	JFrame buttonPanelWindow;
	
	//.. menu and related	
	JMenuBar menuBar;
	JMenu shapeMenu;
	MenuItemHandler menuItemHandler;
	JMenuItem shapeMenuItem1;
	JMenuItem shapeMenuItem2;
	JMenuItem shapeMenuItem3;
	JMenuItem shapeMenuItem4;
	JMenuItem shapeMenuItem5;
	JMenuItem shapeMenuItem6;
	JMenuItem shapeMenuItem7;
	JMenuItem shapeMenuItem8;
	JMenuItem shapeMenuItem9;
	JMenuItem shapeMenuItem10;
	
	// buttons
	JButton[][] buttons;

	//==============================================================================================
	//==== Constructor
	
	// constructor
	public ButtonPanel(DataBox dbox) {
		this.dbox = dbox;
		// create button panel window
		buttonPanelWindow = new JFrame("Bouncing Shapes buttonPanel");
		
		// setup menu bar, buttons, and button panel window
		setupMenuBar();
		setupButtons();
		setupButtonPanelWindow();
	}

	//==============================================================================================
	//==== Setup Functions
	
	// setup menu bar
	public void setupMenuBar() {
		// create menu bar and shape menu
		menuBar = new JMenuBar();
		shapeMenu = new JMenu("Choose Shapes...");
		
		// instantiate menu item action handler
		menuItemHandler = new MenuItemHandler(dbox);
		
		// set shape menu text color
		shapeMenu.setForeground(Color.WHITE);
		
		// create shape menu items
		shapeMenuItem1 = new JMenuItem("Squares");
		shapeMenuItem2 = new JMenuItem("Triangles");
		shapeMenuItem3 = new JMenuItem("Octogons");
		shapeMenuItem4 = new JMenuItem("Hexadecagons");
		shapeMenuItem5 = new JMenuItem("Four-Point Stars");
		shapeMenuItem6 = new JMenuItem("Eight-Point Stars");
		shapeMenuItem7 = new JMenuItem("Mix1 - All Shapes");
		shapeMenuItem8 = new JMenuItem("Mix2 - Hexadec, Four & Eight Point");
		shapeMenuItem9 = new JMenuItem("Mix3 - Four & Eight Point");
		shapeMenuItem10 = new JMenuItem("Mix4 - Square & Eight Point");
		
		// register menu item action handler with shape menu items
    	shapeMenuItem1.addActionListener(menuItemHandler);
    	shapeMenuItem2.addActionListener(menuItemHandler);
    	shapeMenuItem3.addActionListener(menuItemHandler);
    	shapeMenuItem4.addActionListener(menuItemHandler);
    	shapeMenuItem5.addActionListener(menuItemHandler);
    	shapeMenuItem6.addActionListener(menuItemHandler);
    	shapeMenuItem7.addActionListener(menuItemHandler);
    	shapeMenuItem8.addActionListener(menuItemHandler);
    	shapeMenuItem9.addActionListener(menuItemHandler);
    	shapeMenuItem10.addActionListener(menuItemHandler);
    	
    	// add shape menu items to shape menu
    	shapeMenu.add(shapeMenuItem1);
    	shapeMenu.add(shapeMenuItem2);
    	shapeMenu.add(shapeMenuItem3);
    	shapeMenu.add(shapeMenuItem4);
    	shapeMenu.add(shapeMenuItem5);
    	shapeMenu.add(shapeMenuItem6);
    	shapeMenu.add(shapeMenuItem7);
    	shapeMenu.add(shapeMenuItem8);
    	shapeMenu.add(shapeMenuItem9);
    	shapeMenu.add(shapeMenuItem10);
    	
    	// setup shape count display panel
    	dbox.shapeCountDisplayPanel.setPreferredSize(new Dimension(100,10));
    	dbox.shapeCountDisplayPanel.setBackground(Color.BLACK);
    	dbox.shapeCountDisplayPanel.setForeground(dbox.colorNumShapesDisplayForeground);
    	
    	// setup and add shape menu and shape count display panel to menu bar
    	menuBar.setBackground(Color.BLACK);
    	menuBar.add(shapeMenu);
    	menuBar.add(Box.createGlue());
    	menuBar.add(dbox.shapeCountDisplayPanel);
    	
    	// set menu bar
    	buttonPanelWindow.setJMenuBar(menuBar);
	}
		
	// setup buttons
	public void setupButtons() {
		// create array of buttons
		buttons = new JButton[10][2];
		
    	// create buttons, add them to this (ButtonPanel), add BtnHandler to each button 
    	for(int i = 0 ; i<10 ; i++){
    		for(int j = 0 ; j<2 ; j++){
    			buttons[i][j] = new JButton("Button " + (i*2 + j) );
    			add(buttons[i][j]);
    			buttons[i][j].addActionListener(dbox.buttonHandler);
    		}
    	}   
    	
    	// more buttons setup
    	setButtonsTextAncillary();
    	setButtonsBackgroundColorAncillary();
    	
    	// set text color for all buttons
    	for(int i = 0 ; i<10 ; i++){
    		for(int j = 0 ; j<2 ; j++){
    			buttons[i][j].setForeground(Color.WHITE);
    		}
    	}
    	// set text color for select buttons
    	buttons[1][0].setForeground(Color.BLACK);
    	buttons[0][1].setForeground(Color.BLACK);
    	buttons[1][1].setForeground(Color.BLACK);  	
    	// change margin on this button so its text will fit
    	buttons[1][0].setMargin(new Insets(0, 0, 0, 0));
	}
	
	// setup button panel window
	public void setupButtonPanelWindow() {
    	// buttonPanel panel and window configurations
    	setBackground(Color.BLACK);
    	setPreferredSize(dbox.buttonPanelPreferredSize);
    	setLayout(new GridLayout(10,2,1,1)); 
    	buttonPanelWindow.setContentPane(this);
    	buttonPanelWindow.pack();
    	buttonPanelWindow.setLocation(dbox.buttonPanelLocation);  
    	buttonPanelWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buttonPanelWindow.setResizable(true);
		buttonPanelWindow.setVisible(true);
	}

	//==============================================================================================
	//==== Ancillary Setup Functions
	
	// set buttons text (ancillary to setupButtons() )
	public void setButtonsTextAncillary() {
    	// set button text for first column
    	buttons[0][0].setText("PAUSE");
    	buttons[1][0].setText("SHADOW EFFECTS");
    	buttons[2][0].setText("TURN BLACK");
    	buttons[3][0].setText("MORE RED");
    	buttons[4][0].setText("MORE GREEN"); 	
    	buttons[5][0].setText("MORE BLUE");  	
    	buttons[6][0].setText("2 MORE");
    	buttons[7][0].setText("10 MORE");
    	buttons[8][0].setText("50 MORE");
    	buttons[9][0].setText("MORE SPIN");
    	// set button text for second column 	
    	buttons[0][1].setText("NORMAL");
    	buttons[1][1].setText("\"SHADOWS\"");
    	buttons[2][1].setText("TURN WHITE");
    	buttons[3][1].setText("LESS RED"); 	
    	buttons[4][1].setText("LESS GREEN");  	
    	buttons[5][1].setText("LESS BLUE");	
    	buttons[6][1].setText("2 FEWER");   	
    	buttons[7][1].setText("10 FEWER"); 	
    	buttons[8][1].setText("50 FEWER");
    	buttons[9][1].setText("LESS SPIN");
	}
	
	// set buttons background colors (ancillary to setupButtons() )
	public void setButtonsBackgroundColorAncillary() {
    	// set background color for first column
    	buttons[0][0].setBackground(dbox.colorPauseButton);
    	buttons[1][0].setBackground(dbox.colorStyleButtons);
    	buttons[2][0].setBackground(dbox.colorTurnBlackWhiteButtons);
    	buttons[3][0].setBackground(dbox.colorMoreLessColorButtons);
    	buttons[4][0].setBackground(dbox.colorMoreLessColorButtons);
    	buttons[5][0].setBackground(dbox.colorMoreLessColorButtons);
    	buttons[6][0].setBackground(dbox.colorMoreFewerShapesButtons);
    	buttons[7][0].setBackground(dbox.colorMoreFewerShapesButtons);
    	buttons[8][0].setBackground(dbox.colorMoreFewerShapesButtons);
    	buttons[9][0].setBackground(dbox.colorMoreLessSpinButtons);
    	// set background color for second column    	
    	buttons[0][1].setBackground(dbox.colorStyleButtons);
    	buttons[1][1].setBackground(dbox.colorStyleButtons);
    	buttons[2][1].setBackground(dbox.colorTurnBlackWhiteButtons);
    	buttons[3][1].setBackground(dbox.colorMoreLessColorButtons);
    	buttons[4][1].setBackground(dbox.colorMoreLessColorButtons);
    	buttons[5][1].setBackground(dbox.colorMoreLessColorButtons);
    	buttons[6][1].setBackground(dbox.colorMoreFewerShapesButtons);
    	buttons[7][1].setBackground(dbox.colorMoreFewerShapesButtons);
    	buttons[8][1].setBackground(dbox.colorMoreFewerShapesButtons);
    	buttons[9][1].setBackground(dbox.colorMoreLessSpinButtons);
	}

}
















































