package package1;

import java.awt.Color;
import java.awt.Graphics;

public class EightPointStar implements Shape{
	
	//==============================================================================================
	//==== Data
	
	DataBox dbox;
	Toolbox tbox;
    Color myColor;
	
    double referenceAngle = 0; 			// increase and repaint to rotate shape									
    int referenceAngleScalar;			// determines the speed at which the shape spins
    double[] initialAngularOffsets 		// used in coordinates calculations
			= {0              , Math.PI / 8     , Math.PI / 4    , 3 * Math.PI / 8 , 
			   Math.PI / 2    , 5 * Math.PI / 8 , 3 * Math.PI / 4, 7 * Math.PI / 8 ,
			   Math.PI        , 9 * Math.PI / 8 , 5 * Math.PI / 4, 11 * Math.PI / 8,
			   3 * Math.PI / 2, 13 * Math.PI / 8, 7 * Math.PI / 4, 15 * Math.PI / 8};
    int sideLength = 80;				// length of side of square (also used in non-square shapes)
    int sideLengthScalar = sideLength / 4;  // multiplies results of trig functions

    int horizontalOffsetScalar;			// determines horizontal speed component 
    int verticalOffsetScalar;			// determines vertical speed component
    int xOrigin, yOrigin ;				// starting x and y coordinates
    int xCurrent, yCurrent;				// current x and y coordinates
    int xOffset = 0, yOffset = 0;   	// adjust to move shape, initialized with dummy values
    
    double[] xCoords = new double[16];  	// x coordinates of square
    double[] yCoords = new double[16];  	// y coordinates of square
    
    int xDirectionToggle = 1, yDirectionToggle = 1;	// moving left or right?  Up or down?
    
	//==============================================================================================
	//==== Constructor
    
    public EightPointStar(int xOrigin, int yOrigin, int myColorIndex, int referenceAngleScalar, 
    		      int horizontalOffsetScalar, int verticalOffsetScalar){
    	tbox = new Toolbox(dbox);
    	this.xOrigin = xOrigin;
    	this.yOrigin = yOrigin;
    	this.myColor = DataBox.colors[myColorIndex];
    	this.referenceAngleScalar = referenceAngleScalar;
    	this.horizontalOffsetScalar= horizontalOffsetScalar;
    	this.verticalOffsetScalar= verticalOffsetScalar;
    }
    
	//==============================================================================================
	//==== Updater Method (Non-Interface)

    // Contains math to update coordinates for EightPointStar instance.
    public void updateCoords(){
    	xCurrent = xOrigin + xOffset * horizontalOffsetScalar;
    	yCurrent = yOrigin + yOffset * verticalOffsetScalar;
    	xCoords[0] = xCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.cos(
    				referenceAngle * referenceAngleScalar + initialAngularOffsets[0]);
    	xCoords[1] = xCurrent + sideLengthScalar * Math.sqrt(8) * Math.cos(
    				referenceAngle * referenceAngleScalar + initialAngularOffsets[1]);
    	xCoords[2] = xCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.cos(
    				referenceAngle * referenceAngleScalar + initialAngularOffsets[2]);
    	xCoords[3] = xCurrent + sideLengthScalar * Math.sqrt(8) * Math.cos(
    				referenceAngle * referenceAngleScalar + initialAngularOffsets[3]);
    	xCoords[4] = xCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.cos(
				referenceAngle * referenceAngleScalar + initialAngularOffsets[4]);
    	xCoords[5] = xCurrent + sideLengthScalar * Math.sqrt(8) * Math.cos(
				referenceAngle * referenceAngleScalar + initialAngularOffsets[5]);
    	xCoords[6] = xCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.cos(
				referenceAngle * referenceAngleScalar + initialAngularOffsets[6]);
    	xCoords[7] = xCurrent + sideLengthScalar * Math.sqrt(8) * Math.cos(
				referenceAngle * referenceAngleScalar + initialAngularOffsets[7]);
    	xCoords[8] = xCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.cos(
				referenceAngle * referenceAngleScalar + initialAngularOffsets[8]);
    	xCoords[9] = xCurrent + sideLengthScalar * Math.sqrt(8) * Math.cos(
				referenceAngle * referenceAngleScalar + initialAngularOffsets[9]);
    	xCoords[10] = xCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.cos(
				referenceAngle * referenceAngleScalar + initialAngularOffsets[10]);
    	xCoords[11] = xCurrent + sideLengthScalar * Math.sqrt(8) * Math.cos(
				referenceAngle * referenceAngleScalar + initialAngularOffsets[11]);
    	xCoords[12] = xCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.cos(
				referenceAngle * referenceAngleScalar + initialAngularOffsets[12]);
    	xCoords[13] = xCurrent + sideLengthScalar * Math.sqrt(8) * Math.cos(
				referenceAngle * referenceAngleScalar + initialAngularOffsets[13]);
    	xCoords[14] = xCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.cos(
				referenceAngle * referenceAngleScalar + initialAngularOffsets[14]);
    	xCoords[15] = xCurrent + sideLengthScalar * Math.sqrt(8) * Math.cos(
				referenceAngle * referenceAngleScalar + initialAngularOffsets[15]);
    	
    	yCoords[0] = yCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.sin(
    				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[0]) );
    	yCoords[1] = yCurrent + sideLengthScalar * Math.sqrt(8) * Math.sin(
  					-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[1]) );
    	yCoords[2] = yCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.sin(
    				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[2]) );
    	yCoords[3] = yCurrent + sideLengthScalar * Math.sqrt(8) * Math.sin(
    				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[3]) );	
    	yCoords[4] = yCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.sin(
				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[4]) );
    	yCoords[5] = yCurrent + sideLengthScalar * Math.sqrt(8) * Math.sin(
				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[5]) );
    	yCoords[6] = yCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.sin(
				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[6]) );
    	yCoords[7] = yCurrent + sideLengthScalar * Math.sqrt(8) * Math.sin(
				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[7]) );
    	yCoords[8] = yCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.sin(
				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[8]) );
    	yCoords[9] = yCurrent + sideLengthScalar * Math.sqrt(8) * Math.sin(
				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[9]) );
    	yCoords[10] = yCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.sin(
				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[10]) );
    	yCoords[11] = yCurrent + sideLengthScalar * Math.sqrt(8) * Math.sin(
				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[11]) );
    	yCoords[12] = yCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.sin(
				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[12]) );
    	yCoords[13] = yCurrent + sideLengthScalar * Math.sqrt(8) * Math.sin(
				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[13]) );
    	yCoords[14] = yCurrent + .5 * sideLengthScalar * Math.sqrt(8) * Math.sin(
				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[14]) );
    	yCoords[15] = yCurrent + sideLengthScalar * Math.sqrt(8) * Math.sin(
				-1*(referenceAngle * referenceAngleScalar + initialAngularOffsets[15]) );
    	
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
				16);
    }

}



