package package1;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle implements Shape{
	
	//==============================================================================================
	//==== Data
	
	DataBox dbox;
	Toolbox tbox;
    Color myColor;
	
    double referenceAngle = 0; 			// increase and repaint to rotate shape									
    int referenceAngleScalar;			// determines the speed at which the shape spins
    double[] initialAngularOffsets 		// used in coordinates calculations
							= {5 * Math.PI / 4, 7 * Math.PI / 4, Math.PI / 4, 3 * Math.PI / 4, };
    int sideLength = 80;				// length of side of square (also used in non-square shapes)
    int sideLengthScalar = sideLength / 4;  // multiplies results of trig functions

    int horizontalOffsetScalar;			// determines horizontal speed component 
    int verticalOffsetScalar;			// determines vertical speed component
    int xOrigin, yOrigin ;				// starting x and y coordinates
    int xCurrent, yCurrent;				// current x and y coordinates
    int xOffset = 0, yOffset = 0;   	// adjust to move shape, initialized with dummy values
    
    double[] xCoords = new double[4];  	// x coordinates of square
    double[] yCoords = new double[4];  	// y coordinates of square
    
    int xDirectionToggle = 1, yDirectionToggle = 1;	// moving left or right?  Up or down?
    
	//==============================================================================================
	//==== Constructor
    
    public Triangle(int xOrigin, int yOrigin, int myColorIndex, int referenceAngleScalar, 
    		      int horizontalOffsetScalar, int verticalOffsetScalar){
    	tbox = new Toolbox(dbox);
    	this.xOrigin = xOrigin;
    	this.yOrigin = yOrigin;
    	this.myColor = DataBox.colors[myColorIndex];
    	this.referenceAngleScalar = (int)(referenceAngleScalar * .25);
    	this.horizontalOffsetScalar= horizontalOffsetScalar;
    	this.verticalOffsetScalar= verticalOffsetScalar;
    }
    
	//==============================================================================================
	//==== Updater Method (Non-Interface)

    // Contains math to update coordinates for Triangle instance.
    public void updateCoords(){
    	xCurrent = xOrigin + xOffset * horizontalOffsetScalar;
    	yCurrent = yOrigin + yOffset * verticalOffsetScalar;
    	
    	xCoords[0] = 
    			xCurrent + sideLengthScalar * Math.sqrt(8) * Math.cos(
    				referenceAngle * referenceAngleScalar + initialAngularOffsets[0]);
    	xCoords[1] = 
    			xCurrent + sideLengthScalar * Math.sqrt(8) * Math.cos(
    				referenceAngle * referenceAngleScalar + initialAngularOffsets[1]);
    	xCoords[2] = 
    			xCurrent + sideLengthScalar * Math.sqrt(8) * Math.cos(
    				referenceAngle * referenceAngleScalar + initialAngularOffsets[2]);
    	
    	yCoords[0] = 
    			yCurrent + sideLengthScalar * Math.sqrt(8) * Math.sin(
    				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[0]) );
    	yCoords[1] = 
    			yCurrent + sideLengthScalar * Math.sqrt(8) * Math.sin(
  					-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[1]) );
    	yCoords[2] = 
    			yCurrent + sideLengthScalar * Math.sqrt(8) * Math.sin(
    				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[2]) );	
    }

	//============================================================================================//
	//==== Interface Overrides (see Shape interface for comments) ================================//
	//============================================================================================//

	//==============================================================================================
	//==== Getters
    
	@Override
	public int getXCurrent() {
		return xCurrent;
	}

	@Override
	public int getYCurrent() {
		return yCurrent;
	}
    
	@Override
	public int getX_DirectionToggle() {
		return xDirectionToggle;
	}
	
	@Override
	public int getY_DirectionToggle() {
		return yDirectionToggle;
	}

	@Override
	public int getReferenceAngleScalar() {
		return referenceAngleScalar;
	}
	
	@Override
	public Color getColor() {
		return myColor;
	}
	
	//==============================================================================================
	//==== Setters
	
	@Override
	public void setX_DirectionToggle(int newX_DirectionToggle) {
		xDirectionToggle = newX_DirectionToggle;
	}

	@Override
	public void setY_DirectionToggle(int newY_DirectionToggle) {
		yDirectionToggle = newY_DirectionToggle;
	}

	@Override
	public void setReferenceAngleScalar(int newReferenceAngleScalar) {
		referenceAngleScalar = newReferenceAngleScalar;
	}

	@Override
	public void setColor(Color color) {
		myColor = color;
	}
	
	//==============================================================================================
	//==== Animation Computations
	
    public void positionUpdate() {
		referenceAngle += referenceAngleScalar * Math.PI / 90;
		xOffset += xDirectionToggle;
		yOffset += yDirectionToggle;
    }
    
    public void updateAndDraw(Graphics g){
		updateCoords();
		g.setColor(myColor);
		g.fillPolygon(
				tbox.convertDoubleArrayToIntArray(xCoords), 
				tbox.convertDoubleArrayToIntArray(yCoords), 
				3);
    }
    
}



