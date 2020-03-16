package package1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuItemHandler implements ActionListener{
	//==============================================================================================
	//==== Data
	
	DataBox dbox;
	Toolbox tbox;
	
	//==============================================================================================
	//==== Constructor
	
	// initialize dbox and instantiate tbox
	public MenuItemHandler(DataBox dbox) {
		this.dbox = dbox;
		tbox = new Toolbox(dbox);
	}
	
	//==============================================================================================
	//==== Menu Item Click Handler

	@Override
	// update shape state and start new animation
	public void actionPerformed(ActionEvent arg0) {
		if      (arg0.getActionCommand() == "Squares") {
			dbox.shapeState = DataBox.ShapeState.SQUARES;
		}
		else if (arg0.getActionCommand() == "Triangles") { 
			dbox.shapeState = DataBox.ShapeState.TRIANGLES;
		}
		else if (arg0.getActionCommand() == "Octogons") { 
			dbox.shapeState = DataBox.ShapeState.OCTOGONS;
		}
		else if (arg0.getActionCommand() == "Hexadecagons") { 
			dbox.shapeState = DataBox.ShapeState.HEXADECAGONS;
		}
		else if (arg0.getActionCommand() == "Four-Point Stars") { 
			dbox.shapeState = DataBox.ShapeState.FOUR_POINT_STARS;
		}
		else if (arg0.getActionCommand() == "Eight-Point Stars") { 
			dbox.shapeState = DataBox.ShapeState.EIGHT_POINT_STARS;
		}
		else if (arg0.getActionCommand() == "Mix1 - All Shapes") { 
			dbox.shapeState = DataBox.ShapeState.MIX1;
		}
		else if (arg0.getActionCommand() == "Mix2 - Hexadec, Four & Eight Point") {
			dbox.shapeState = DataBox.ShapeState.MIX2;
		}
		else if (arg0.getActionCommand() == "Mix3 - Four & Eight Point") { 
			dbox.shapeState = DataBox.ShapeState.MIX3;
		}
		else if (arg0.getActionCommand() == "Mix4 - Square & Eight Point") { 
			dbox.shapeState = DataBox.ShapeState.MIX4;
		}		
		tbox.startNewAnimation();
	}

}
