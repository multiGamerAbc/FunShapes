package package1;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Toolbox {
	

	
	//==============================================================================================
	//==== Data
	
	DataBox dbox;
	MathStuff myMath = new MathStuff();
	Rectangle graphicsConfigurationRectangle;
	
	//==============================================================================================
	//==== Constructor
	
	// initialize data box
	public Toolbox(DataBox dbox) {
		this.dbox = dbox;
	}
		
	//==============================================================================================
	//==== Animation Logic
	
	// Test Shape to see if it' center hit a wall. If so, reverse appropriate direction component.
	public void wallTest(Shape shape){
		int xCurrent = shape.getXCurrent();
		int yCurrent = shape.getYCurrent();
		if( xCurrent > dbox.animationPreferredSize.width || xCurrent < 0 ){
			shape.setX_DirectionToggle(-shape.getX_DirectionToggle());
		}
		if( yCurrent > dbox.animationPreferredSize.height || yCurrent < 0 ){
			shape.setY_DirectionToggle(-shape.getY_DirectionToggle());
		}
	}

	//==============================================================================================
	//==== Animation Computations

	// start a new animation according to animation style
	public void startNewAnimation() {
		switch(dbox.animationStyle) {
		case DataBox.SHADOWLESS:
			dbox.shapeList 
				= packConstructorArrayAndGetShapeList(dbox.numberOfShapesResident);
			break;
		case DataBox.SHADOWS:
			dbox.shapeList 
				= packConstructorArrayAndGetShapeListWithShadows(dbox.numberOfShapesResident);
			break;
		case DataBox.SHADOW_EFFECTS:
			dbox.shapeList 
				= packConstructorArrayAndGetShapeListWithShadowEffects(dbox.numberOfShapesResident);
			break;	
		}
	}

	// Creates, loads, and returns an ArrayList<Shape> with n Triangles semi-randomly
    public ArrayList<Shape> packConstructorArrayAndGetShapeList(int numShapes){
    	int[][] shapeConstructorData = new int[numShapes][6];
	
    	// shapeConstructorData indexes =>
    	// 0: starting x            1: starting y         2: color as index 
    	// 3: referenceAngleScalar  4: horizOffsetScalar  5: vertOffsetScalar
    	for(int i = 0 ; i < numShapes ; i++){
    		shapeConstructorData[i][0] = dbox.startingX;
    		shapeConstructorData[i][1] = dbox.startingY;
    		shapeConstructorData[i][2] = (int)(Math.random()*DataBox.colors.length - 1);
    		shapeConstructorData[i][3] = (int)(Math.random()*dbox.scalarA) - dbox.shifterA;
    		shapeConstructorData[i][4] = (int)(Math.random()*dbox.scalarA) - dbox.shifterB;
    		shapeConstructorData[i][5] = (int)(Math.random()*dbox.scalarA) - dbox.shifterB;
    	}    	
    	return createShapeList(numShapes, shapeConstructorData);
    }
    
	// Creates, loads, and returns an ArrayList<Shape> with n Squares semi-randomly    
    public ArrayList<Shape> packConstructorArrayAndGetShapeListWithShadows(int numShapes){
    	int[][] shapeConstructorData = new int[numShapes][6];

    	// shapeConstructorData indexes =>
    	// 0: starting x            1: starting y         2: color as index 
    	// 3: referenceAngleScalar  4: horizOffsetScalar  5: vertOffsetScalar
    	for(int i = 0 ; i < numShapes ; i++){
    		shapeConstructorData[i][0] = dbox.startingX;
    		shapeConstructorData[i][1] = dbox.startingY;
    		if(i % 2 == 0){		// colored shape
    			shapeConstructorData[i][2] = (int)(Math.random()*DataBox.colors.length - 1);
    		} else {			// "shadow" shape
    			shapeConstructorData[i][2] = DataBox.colors.length - 1;
    		}
    		shapeConstructorData[i][3] = (int)(Math.random()*dbox.scalarA) - dbox.shifterA;
    		shapeConstructorData[i][4] = (int)(Math.random()*dbox.scalarA) - dbox.shifterB;
    		shapeConstructorData[i][5] = (int)(Math.random()*dbox.scalarA) - dbox.shifterB;
    	}
    	return createShapeList(numShapes, shapeConstructorData);
    }

 	// Creates, loads, and returns an ArrayList<Shape> with n Squares semi-randomly    
    public ArrayList<Shape> packConstructorArrayAndGetShapeListWithShadowEffects(int numShapes){
    	int halfTheNumberOfShapes = numShapes/2;
     	int[][] shapeConstructorData = new int[halfTheNumberOfShapes][6];           
        // like shapeConstructorData, but each Shape is Black with different referenceAngleScalar
     	int[][] shapeConstructorDataForShadows = new int[halfTheNumberOfShapes][6];                            
     	
     	double[] randomizingScalarsForShadows = new double[halfTheNumberOfShapes];  
    	for(int i = 0 ; i < halfTheNumberOfShapes ; i++){  
    		randomizingScalarsForShadows[i] 
    			= .5 * myMath.randomizingScalar(Math.random());  }
     	
    	// shapeConstructorData.. indexes =>
    	// 0: starting x            1: starting y         2: color as index 
    	// 3: referenceAngleScalar  4: horizOffsetScalar  5: vertOffsetScalar
     	for(int i = 0 ; i < halfTheNumberOfShapes ; i++){
     		shapeConstructorData[i][0] = dbox.startingX;
     		shapeConstructorData[i][1] = dbox.startingY;	
         	shapeConstructorData[i][2] = (int)(Math.random()*DataBox.colors.length - 1);
     		shapeConstructorData[i][3] = (int)(Math.random()*dbox.scalarA) - dbox.shifterA;
     		shapeConstructorData[i][4] = (int)(Math.random()*dbox.scalarA) - dbox.shifterB;
     		shapeConstructorData[i][5] = (int)(Math.random()*dbox.scalarA) - dbox.shifterB;
     	}    	
     	for(int i = 0 ; i < halfTheNumberOfShapes ; i++){
     		shapeConstructorDataForShadows[i][0] = shapeConstructorData[i][0];
     		shapeConstructorDataForShadows[i][1] = shapeConstructorData[i][1];
     		shapeConstructorDataForShadows[i][2] = DataBox.colors.length - 1;  // black
     		if(randomizingScalarsForShadows[i] < .50){
     			shapeConstructorDataForShadows[i][3] 
     					= (int)( -1 * randomizingScalarsForShadows[i] * dbox.scalarA );
     		} else {
     			shapeConstructorDataForShadows[i][3] 
     					= (int)(  1 * randomizingScalarsForShadows[i] * dbox.scalarA );
     		}
     		shapeConstructorDataForShadows[i][4] = shapeConstructorData[i][4];
     		shapeConstructorDataForShadows[i][5] = shapeConstructorData[i][5];
     	}

     	ArrayList<Shape> shapes 
     		= createShapeList(halfTheNumberOfShapes, shapeConstructorData);
     	ArrayList<Shape> shadowEffects 
     		= createShapeList(halfTheNumberOfShapes, shapeConstructorDataForShadows);
     	
     	return mergeStaggered(shapes, shadowEffects);
    }
   
    // create a shape object
    public Shape createShape(int shape, int[] args) {
    	switch(shape) {
    	case DataBox.SQUARE:
    		return new Square(args[0], args[1], args[2], args[3], args[4], args[5]);
    	case DataBox.TRIANGLE:
    		return new Triangle(args[0], args[1], args[2], args[3], args[4], args[5]);
    	case DataBox.OCTOGON:
    		return new Octogon(args[0], args[1], args[2], args[3], args[4], args[5]);
    	case DataBox.HEXADECAGON:
    		return new Hexadecagon(args[0], args[1], args[2], args[3], args[4], args[5]);
    	case DataBox.FOUR_POINT_STAR:
    		return new FourPointStar(args[0], args[1], args[2], args[3], args[4], args[5]);
    	case DataBox.EIGHT_POINT_STAR:
    		return new EightPointStar(args[0], args[1], args[2], args[3], args[4], args[5]);		
		default:
			return new Square(args[0], args[1], args[2], args[3], args[4], args[5]);
    	}
    }
    
    // create a shape list
    ArrayList<Shape> createShapeList(int numShapes, int[][] shapeConstructorData){
    	ArrayList<Shape> result = new ArrayList<Shape>();
    	switch(dbox.shapeState) {
    	case SQUARES:
	    	for (int i = 0 ; i < numShapes ; i++){
	    		result.add(createShape(DataBox.SQUARE, shapeConstructorData[i])); }
	    	break;
    	case TRIANGLES:
	    	for (int i = 0 ; i < numShapes ; i++){
	    		result.add(createShape(DataBox.TRIANGLE, shapeConstructorData[i])); }
	    	break;
    	case OCTOGONS:
	    	for (int i = 0 ; i < numShapes ; i++){
	    		result.add(createShape(DataBox.OCTOGON, shapeConstructorData[i])); }
	    	break;
    	case HEXADECAGONS:
	    	for (int i = 0 ; i < numShapes ; i++){
	    		result.add(createShape(DataBox.HEXADECAGON, shapeConstructorData[i])); }
	    	break;
    	case FOUR_POINT_STARS:
	    	for (int i = 0 ; i < numShapes ; i++){
	    		result.add(createShape(DataBox.FOUR_POINT_STAR, shapeConstructorData[i])); }
	    	break;
    	case EIGHT_POINT_STARS:
	    	for (int i = 0 ; i < numShapes ; i++){
	    		result.add(createShape(DataBox.EIGHT_POINT_STAR, shapeConstructorData[i])); }
	    	break;
    	case MIX1:
	    	for (int i = 0 ; i < numShapes ; i++){
	    		result.add(createShape((int)(Math.random()*6), shapeConstructorData[i])); }
	    	break;
    	case MIX2:
	    	for (int i = 0 ; i < numShapes ; i++){
	    		result.add(createShape(3+(int)(Math.random()*3), shapeConstructorData[i])); }
	    	break;
    	case MIX3:
	    	for (int i = 0 ; i < numShapes ; i++){
	    		result.add(createShape(4+(int)(Math.random()*2), shapeConstructorData[i])); }
	    	break;
    	case MIX4:
	    	for (int i = 0 ; i < numShapes ; i++){
	    		result.add(createShape(Math.random() < .5 ? 
	    				DataBox.SQUARE : DataBox.EIGHT_POINT_STAR, shapeConstructorData[i])); }
	    	break;
    	}
    	return result;
    }

    // add new shapes to the resident shape list
	public void addNewShapes(int numNewShapes) {
		ArrayList<Shape> newShapeList = new ArrayList<Shape>();
		if(dbox.animationStyle == DataBox.SHADOWLESS) {
			newShapeList = packConstructorArrayAndGetShapeList(numNewShapes);
		} else if (dbox.animationStyle == DataBox.SHADOW_EFFECTS) {
			newShapeList = packConstructorArrayAndGetShapeListWithShadowEffects(numNewShapes);
		} else if (dbox.animationStyle == DataBox.SHADOWS) {
			newShapeList = packConstructorArrayAndGetShapeListWithShadows(numNewShapes);
		}
		if(newShapeList.size() > 0) { dbox.shapeList.addAll(newShapeList); }
	}

    // merge two shape lists into one by staggering (used for shadow effects)
    public ArrayList<Shape> mergeStaggered(ArrayList<Shape> shapes, ArrayList<Shape> shadowEffects){
    	ArrayList<Shape> result = new ArrayList<Shape>();
    	int size = min(shapes.size(), shadowEffects.size());
    	for(int i = 0 ; i < size ; i++) {
    		result.add(shapes.get(i));
    		result.add(shadowEffects.get(i));
    	}
    	return result;
    }
	
	//==============================================================================================
	//==== Window Configuration Tools
	
	// Get a list of Rectangles representing monitors 
	ArrayList<Rectangle> getGraphicsConfigurationRectangles() {
		ArrayList<Rectangle> graphicsConfigRectangles = new ArrayList<Rectangle>();
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devs = env.getScreenDevices();	
		for(GraphicsDevice dev : devs) {
			graphicsConfigRectangles.add(dev.getConfigurations()[0].getBounds());
		}
		return graphicsConfigRectangles;
	}
	
	// configure application windows based on monitor configuration information
	public void initiallyConfigureWindows() {
		ArrayList<Rectangle> graphicsConfigRectangles = getGraphicsConfigurationRectangles();
		if(graphicsConfigRectangles.size() == 1) {			// one monitor
			graphicsConfigurationRectangle = graphicsConfigRectangles.get(0);
			boolean isLandscape 
					= graphicsConfigurationRectangle.width >= graphicsConfigurationRectangle.height;

			if(isLandscape) {
				// animation on right half, buttons to the left (default for landscape monitor)
				oneScreenAnimationOnRightButtonsOnLeft(graphicsConfigurationRectangle);
			} else {
				// animation on top half, buttons just below (default for portrait monitor)
				oneScreenAnimationOnTopButtonsOnBottom(graphicsConfigurationRectangle);
			}
		} else if (graphicsConfigRectangles.size() >= 2) {	// two or more monitors (treat as two)
			if(   graphicsConfigRectangles.get(0).width > graphicsConfigRectangles.get(0).height 
			   && graphicsConfigRectangles.get(1).width > graphicsConfigRectangles.get(1).height) 
			{	// both are landscape
				bothAreLandscape(graphicsConfigRectangles);
			} else if 
				(   graphicsConfigRectangles.get(0).height > graphicsConfigRectangles.get(0).width 
				 && graphicsConfigRectangles.get(1).height > graphicsConfigRectangles.get(1).width)
			{	// both are portrait	
				bothArePortrait(graphicsConfigRectangles);
			} else if
				(   graphicsConfigRectangles.get(0).width  > graphicsConfigRectangles.get(0).height 
				 && graphicsConfigRectangles.get(1).height > graphicsConfigRectangles.get(1).width)
			{	// left is landscape, right is portrait
				leftIsLandscapeRightIsPortrait(graphicsConfigRectangles);
			} else if 
				(  graphicsConfigRectangles.get(0).height > graphicsConfigRectangles.get(0).width 
				&& graphicsConfigRectangles.get(1).width  > graphicsConfigRectangles.get(1).height)
			{	// left is portrait, right is landscape
				leftIsPortraitRightIsLandscape(graphicsConfigRectangles);
			}
		}
	}

	// (six functions) - set program window configuration variables for one configuration
	public void oneScreenAnimationOnRightButtonsOnLeft(Rectangle graphicsConfigurationRectangle) {
		// animation panel..
		dbox.animationPanelLocation = new Point(
				graphicsConfigurationRectangle.x + graphicsConfigurationRectangle.width/3,
				graphicsConfigurationRectangle.y + dbox.interWindowBuffer/2);
		dbox.animationPreferredSize = new Dimension(
				(2 * graphicsConfigurationRectangle.width)/3 - dbox.interWindowBuffer/2, 
				graphicsConfigurationRectangle.height - dbox.interWindowBuffer/2 
					- dbox.taskbarBuffer);
		dbox.animationPanelLeftEdge = dbox.animationPanelLocation.x;
		dbox.animationPanelTopEdge = dbox.animationPanelLocation.y;
		dbox.animationPanelRightEdge 
			= dbox.animationPanelLeftEdge + dbox.animationPreferredSize.width;
		
		// button panel..
		dbox.buttonPanelLocation = new Point(
				graphicsConfigurationRectangle.x + dbox.interWindowBuffer, 
				graphicsConfigurationRectangle.y 
					+ (graphicsConfigurationRectangle.height - dbox.taskbarBuffer)/2 
					+ dbox.interWindowBuffer/2);
		dbox.buttonPanelPreferredSize = new Dimension(
				graphicsConfigurationRectangle.width/3 - 2*dbox.interWindowBuffer,
				(graphicsConfigurationRectangle.height - dbox.taskbarBuffer)/2 
					- dbox.interWindowBuffer/2);
	}	
	public void oneScreenAnimationOnTopButtonsOnBottom(Rectangle graphicsConfigurationRectangle) {
		// animation panel..
		dbox.animationPanelLocation = new Point(
			graphicsConfigurationRectangle.x + dbox.interWindowBuffer/2,
			graphicsConfigurationRectangle.y + dbox.interWindowBuffer/2 );
		dbox.animationPreferredSize = new Dimension(
			graphicsConfigurationRectangle.width - 2*dbox.interWindowBuffer, 
			graphicsConfigurationRectangle.height/2 - dbox.interWindowBuffer);
		dbox.animationPanelLeftEdge = dbox.animationPanelLocation.x;
		dbox.animationPanelTopEdge = dbox.animationPanelLocation.y;
		dbox.animationPanelRightEdge 
			= dbox.animationPanelLeftEdge + dbox.animationPreferredSize.width;
		
		// button panel..
		dbox.buttonPanelLocation = new Point(
			graphicsConfigurationRectangle.x + dbox.interWindowBuffer/2, 
			graphicsConfigurationRectangle.y + dbox.interWindowBuffer 
				+ graphicsConfigurationRectangle.height/2 + dbox.windowHeaderHeightBuffer);
		dbox.buttonPanelPreferredSize = new Dimension(
			graphicsConfigurationRectangle.width - 2*dbox.interWindowBuffer,
			(graphicsConfigurationRectangle.height - dbox.taskbarBuffer)/3 
				- dbox.interWindowBuffer/2);
	}
	public void leftIsPortraitRightIsLandscape(ArrayList<Rectangle> graphicsConfigRectangles) {
		Rectangle rectangleLeft  =  graphicsConfigRectangles.get(0);
		Rectangle rectangleRight =  graphicsConfigRectangles.get(1);
		
		// animation panel..
		dbox.animationPanelLocation = new Point(
			rectangleRight.x + dbox.interWindowBuffer/2,
			rectangleRight.y + dbox.interWindowBuffer/2 );
		dbox.animationPreferredSize = new Dimension(
			rectangleRight.width - 2*dbox.interWindowBuffer, 
			rectangleRight.height - dbox.interWindowBuffer - dbox.taskbarBuffer);

		dbox.animationPanelLeftEdge = dbox.animationPanelLocation.x;
		dbox.animationPanelTopEdge = dbox.animationPanelLocation.y;
		dbox.animationPanelRightEdge 
			= dbox.animationPanelLeftEdge + dbox.animationPreferredSize.width;
		
		// button panel..
		dbox.buttonPanelLocation = new Point(
			rectangleLeft.x + 2*rectangleLeft.width/3 - dbox.interWindowBuffer, 
			rectangleLeft.y + rectangleLeft.height/2 );
		dbox.buttonPanelPreferredSize = new Dimension(
			rectangleLeft.width/3,
			(rectangleLeft.height)/3 );
	}
	public void leftIsLandscapeRightIsPortrait(ArrayList<Rectangle> graphicsConfigRectangles) {
		Rectangle rectangleLeft  =  graphicsConfigRectangles.get(0);
		Rectangle rectangleRight =  graphicsConfigRectangles.get(1);
		
		// animation panel..
		dbox.animationPanelLocation = new Point(
			rectangleLeft.x + dbox.interWindowBuffer/2,
			rectangleLeft.y + dbox.interWindowBuffer/2 );
		dbox.animationPreferredSize = new Dimension(
			rectangleLeft.width - 2*dbox.interWindowBuffer, 
			rectangleLeft.height - dbox.interWindowBuffer - dbox.taskbarBuffer);
		dbox.animationPanelLeftEdge = dbox.animationPanelLocation.x;
		dbox.animationPanelTopEdge = dbox.animationPanelLocation.y;
		dbox.animationPanelRightEdge 
			= dbox.animationPanelLeftEdge + dbox.animationPreferredSize.width;
		
		// button panel..
		dbox.buttonPanelLocation = new Point(
				rectangleRight.x, 
				rectangleRight.y + rectangleRight.height/2 );
		dbox.buttonPanelPreferredSize = new Dimension(
				rectangleRight.width/3, rectangleRight.height/3 );
	}
	public void bothAreLandscape(ArrayList<Rectangle> graphicsConfigRectangles) {
		Rectangle rectangleLeft  =  graphicsConfigRectangles.get(0);
		Rectangle rectangleRight =  graphicsConfigRectangles.get(1);
		
		// animation panel..
		dbox.animationPanelLocation = new Point(
			rectangleRight.x + dbox.interWindowBuffer/2,
			rectangleRight.y + dbox.interWindowBuffer/2 );
		dbox.animationPreferredSize = new Dimension(
			rectangleRight.width - 2*dbox.interWindowBuffer, 
			rectangleRight.height - dbox.interWindowBuffer - dbox.taskbarBuffer);
		dbox.animationPanelLeftEdge = dbox.animationPanelLocation.x;
		dbox.animationPanelTopEdge = dbox.animationPanelLocation.y;
		dbox.animationPanelRightEdge 
			= dbox.animationPanelLeftEdge + dbox.animationPreferredSize.width;
		
		// button panel..
		dbox.buttonPanelLocation = new Point(
			rectangleLeft.x + 3*rectangleLeft.width/4 - dbox.interWindowBuffer, 
			rectangleLeft.y + rectangleLeft.height/4 );
		dbox.buttonPanelPreferredSize = new Dimension(
			rectangleLeft.width/4,
			(rectangleLeft.height)/2 );
	}	
	public void bothArePortrait(ArrayList<Rectangle> graphicsConfigRectangles) {
		Rectangle rectangleLeft  =  graphicsConfigRectangles.get(0);
		Rectangle rectangleRight =  graphicsConfigRectangles.get(1);
		
		// animation panel..
		dbox.animationPanelLocation = new Point(
			rectangleRight.x + dbox.interWindowBuffer/2,
			rectangleRight.y + dbox.interWindowBuffer/2 );
		dbox.animationPreferredSize = new Dimension(
			rectangleRight.width - 2*dbox.interWindowBuffer, 
			rectangleRight.height - dbox.interWindowBuffer - dbox.taskbarBuffer);
		dbox.animationPanelLeftEdge = dbox.animationPanelLocation.x;
		dbox.animationPanelTopEdge = dbox.animationPanelLocation.y;
		dbox.animationPanelRightEdge 
			= dbox.animationPanelLeftEdge + dbox.animationPreferredSize.width;
		
		// button panel..
		dbox.buttonPanelLocation = new Point(
			rectangleLeft.x + 2*rectangleLeft.width/3 - dbox.interWindowBuffer, 
			rectangleLeft.y + rectangleLeft.height/2 );
		dbox.buttonPanelPreferredSize = new Dimension(
			rectangleLeft.width/3,
			(rectangleLeft.height)/3 );
	}

	//==============================================================================================
	//==== Miscellaneous Tools
    
    // Converts double[] to int[]
    public int[] convertDoubleArrayToIntArray(double[] doubles){
    	int len = doubles.length;
    	int[] result = new int[len];
    	for (int i = 0 ; i < len ; i++){
    		result[i] = (int)doubles[i];
    	}
    	return result;
    }
    
	// A function to return the minimum of two integers
	public int min(int a, int b){
		if (a < b){ 		return a;
		} else {			return b;
		}
	}
	
	// A function to return the minimum of two integers
	public int max(int a, int b){
		if (a > b){ 		return a;
		} else {			return b;
		}
	}
}





























