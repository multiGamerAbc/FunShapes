package package1;

import java.awt.Color;
import java.awt.Graphics;

// interface for shape objects to be used in animation
public interface Shape {
	
	//==============================================================================================
	//==== Getters
	
	public int getXCurrent();				// current x coordinate?
	public int getYCurrent();				// current y coordinate?
	
	public int getX_DirectionToggle();		// moving left or right?
	public int getY_DirectionToggle();		// moving up or down?
	public int getReferenceAngleScalar();	// how fast is the shape's spin?
	public Color getColor();				// what is the shape's color?
	
	//==============================================================================================
	//==== Setters
	
	public void setX_DirectionToggle(int newX_DirectionToggle);			// set moving left or right
	public void setY_DirectionToggle(int newY_DirectionToggle);			// set moving up or down
	public void setReferenceAngleScalar(int newReferenceAngleScalar);	// set spin speed
	public void setColor(Color color);									// set color
	
	//==============================================================================================
	//==== Animation Computations
	
	public void positionUpdate();			// update shape's position
	public void updateAndDraw(Graphics g);	// update coordinates, fill polygon
}
