package package1;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ShapeCountDisplayPanel extends JPanel{
	//==============================================================================================
	//==== Data
	
	DataBox dbox;
	
	//==============================================================================================
	//==== Constructor
	
	public ShapeCountDisplayPanel(DataBox dbox) {
		this.dbox = dbox;
	}
	
	//==============================================================================================
	//==== Overrides
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("# of Shapes: " + dbox.numberOfShapesResident , 5,15);
	}
}
