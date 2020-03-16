package package1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.Timer;

public class DataBox {
	//-- Note: the below categorizations do not indicate exclusivity of use --//
	
	//==============================================================================================
	//==== Bouncing Shapes More System-Wide Data
	
	Toolbox tbox = new Toolbox(this);  			// toolbox
	ArrayList<Shape> shapeList;					// list of resident shapes
	Timer frameTimer;							// timer to drive animation
	
	int numberOfShapesResident = 10;			// number of shapes which are resident

	// constants which indicate animation style
	final static int SHADOWLESS = 0, SHADOWS = 1, SHADOW_EFFECTS = 2;
	int animationStyle = SHADOWLESS;			// indicates animation style
	
	// constants which indicate a shape (for 
	final static int SQUARE = 0, TRIANGLE = 1, OCTOGON = 2, HEXADECAGON = 3, FOUR_POINT_STAR = 4,
			EIGHT_POINT_STAR = 5;
	
	// enum which indicates what shapes will be packed into resident shape list
	enum ShapeState{
		SQUARES, TRIANGLES, OCTOGONS, HEXADECAGONS, FOUR_POINT_STARS,
		EIGHT_POINT_STARS, MIX1, MIX2, MIX3, MIX4;
	}
	ShapeState shapeState = ShapeState.SQUARES;  // indicates shapes to be packed into shape list
	
	// a color to add to colors array
	static Color myTeal = new Color(4, 163, 157);
	// the set of possible initial colors for the shapes in new shape lists
	static Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA,
			Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW,
			myTeal, Color.BLACK};
	
	//==============================================================================================
	//==== Bouncing Shapes System-Wide Window Layout Configuration Data
	
	int taskbarBuffer = 80;						// accounts for Windows taskbar
	int interWindowBuffer = 20;					// added for space around program windows
	int windowHeaderHeightBuffer = 24;			// accounts for program window header
	
	//==============================================================================================
	//==== Animation Panel Data 
	
	int timerDelay = 100;						// initialize animation timer with 1/10 second
	
	//.. animation panel window configuration data
	Point animationPanelLocation;
	Dimension animationPreferredSize;
	Color animationPanelBackgroundColor = Color.BLACK;
	
	//.. used for wall test (testing whether a shape reaches an edge of its window)
	int animationPanelLeftEdge, animationPanelRightEdge;
	int animationPanelTopEdge, animationPanelBottomEdge;
	
	//.. various shape positional data
	int startingX = 1;
	int startingY = 250;
	int scalarA = 50;
	int scalarB = 20;
	int scalarC = 25;
	int shifterA = 20;
	int shifterB = 25;
	
	//==============================================================================================
	//==== buttonPanel Data 

	// handles button click events
	BtnHandler buttonHandler = new BtnHandler(this);
	
	//.. button panel window configuration data
	Point buttonPanelLocation;
	Dimension buttonPanelPreferredSize;

	// displays the number of resident shapes on the menu bar
	ShapeCountDisplayPanel shapeCountDisplayPanel;

	//.. button panel colors
	Color colorPauseButton = new Color(230, 68, 9);
	Color colorStyleButtons = new Color(245, 141, 66);
	Color colorTurnBlackWhiteButtons = new Color(138, 129, 127);
	Color colorMoreLessColorButtons = new Color(94, 85, 82);
	Color colorMoreFewerShapesButtons = new Color(45, 84, 107);
	Color colorMoreLessSpinButtons = new Color(14, 103, 156);
	Color colorNumShapesDisplayForeground = new Color(107, 191, 242);
	
	//==============================================================================================
	//==== BtnHandler Data
	
	// used in color updates
	int colorScalar = 128;
	
	//==============================================================================================
	//==== Constructor

	// create initial shape list, configure windows, instantiate shape count display panel
	public DataBox() {
		shapeList = tbox.packConstructorArrayAndGetShapeList(numberOfShapesResident);
		tbox.initiallyConfigureWindows();
		shapeCountDisplayPanel = new ShapeCountDisplayPanel(this);
	}
	
}


