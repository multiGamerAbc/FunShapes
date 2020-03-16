package package1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtnHandler implements ActionListener{
	
	//==============================================================================================
	//==== Data
	
	DataBox dbox;
	Toolbox tbox;

    final int ADD_RED = 0, ADD_GREEN = 1, ADD_BLUE = 2;
    final int REM_RED = 3, REM_GREEN = 4, REM_BLUE = 5;	
	boolean pauseToggle = true;
	
	//==============================================================================================
	//==== Constructor
	
	public BtnHandler(DataBox dbox) {
		this.dbox = dbox;
		tbox = new Toolbox(dbox);
	}
	  
	//==============================================================================================
	//==== Button Click Handler
	
	// invoke a method according to action command from clicked button
	public void actionPerformed(ActionEvent evt){
		// first column of buttons
        if(     evt.getActionCommand() == "PAUSE") 			{   pauseUnpauseAnimation(); 		}
        else if(evt.getActionCommand() == "SHADOW EFFECTS"){ 	
        	updateAnimationStyle(DataBox.SHADOW_EFFECTS);										}
        else if(evt.getActionCommand() == "TURN BLACK"){  	   	turnShapesBlack();				}
        else if(evt.getActionCommand() == "MORE RED"){  	    updateShapesColors(ADD_RED);	}
        else if(evt.getActionCommand() == "MORE GREEN"){ 		updateShapesColors(ADD_GREEN);	}
        else if(evt.getActionCommand() == "MORE BLUE"){  		updateShapesColors(ADD_BLUE);	}
        else if(evt.getActionCommand() == "2 MORE") {    		changeNumberOfShapes(2); 		}
        else if(evt.getActionCommand() == "10 MORE") {   		changeNumberOfShapes(10);		}
        else if(evt.getActionCommand() == "50 MORE") {  		changeNumberOfShapes(50); 		}
        else if(evt.getActionCommand() == "MORE SPIN"){		   	speedupShapesSpin();			}
		// second column of buttons        
        else if(evt.getActionCommand() == "NORMAL") {           
        	updateAnimationStyle(DataBox.SHADOWLESS); 											}
        else if(evt.getActionCommand() == "\"SHADOWS\""){  	   	
        	updateAnimationStyle(DataBox.SHADOWS);												}
        else if(evt.getActionCommand() == "TURN WHITE"){        turnShapesWhite();				}
        else if(evt.getActionCommand() == "LESS RED"){   	   	updateShapesColors(REM_RED);	}
        else if(evt.getActionCommand() == "LESS GREEN"){  	   	updateShapesColors(REM_GREEN);	}
        else if(evt.getActionCommand() == "LESS BLUE"){  	   	updateShapesColors(REM_BLUE);	}
        else if(evt.getActionCommand() == "2 FEWER") {    		changeNumberOfShapes(-2); 		}
        else if(evt.getActionCommand() == "10 FEWER") {   		changeNumberOfShapes(-10);		}
        else if(evt.getActionCommand() == "50 FEWER") {  		changeNumberOfShapes(-50); 		}
        else if(evt.getActionCommand() == "LESS SPIN"){  	   	slowDownShapesSpin();			}
        else {  System.out.println("Error - unknown action command: " + evt.getActionCommand()); 									}
	}
 
	// set the animation style: shadowless, shadows, or shadow-effects
	public void updateAnimationStyle(int animationStyle) {
		switch(animationStyle) {
		case DataBox.SHADOWLESS:
	    	dbox.animationStyle = DataBox.SHADOWLESS;
			break;
		case DataBox.SHADOWS:
	    	dbox.animationStyle = DataBox.SHADOWS;
			break;
		case DataBox.SHADOW_EFFECTS:
	    	dbox.animationStyle = DataBox.SHADOW_EFFECTS;
			break;
		}	
		tbox.startNewAnimation();
	}
		
	//==============================================================================================
	//==== Shape List Length Modifier

	// change the number of resident shapes
    public void changeNumberOfShapes(int increment) {
    	if(increment < 0) {	// removing some shapes
    		int numberRequestedToHaveRemoved = Math.abs(increment);
    		int numberToBeRemoved = tbox.min(dbox.shapeList.size(), numberRequestedToHaveRemoved);
    		dbox.numberOfShapesResident -= numberToBeRemoved;
    		for(int i = 0 ; i < numberToBeRemoved ; i++) {
    			dbox.shapeList.remove(0);
    		}
    	} else {			// adding some shapes
    		dbox.numberOfShapesResident += increment;
    		tbox.addNewShapes(increment);
    	}
    	dbox.shapeCountDisplayPanel.repaint();
    }
	
    //==============================================================================================
	//==== Shape List Color Modifiers
    
    // update the color of resident shapes by incrementing one color component by a random amount
    public void updateShapesColors(int colorUpdateMode) {
  	    int randomIncrement;
    	for(Shape shape : dbox.shapeList){
    		Color thisShapesColor = shape.getColor();
  		    int r = thisShapesColor.getRed();
  		    int g = thisShapesColor.getGreen();
  		    int b = thisShapesColor.getBlue();
  		    randomIncrement = (int)(Math.random()*dbox.colorScalar);
  		    switch (colorUpdateMode) {
  		    case ADD_RED:
  		    	if(r < 255)
  		    		shape.setColor(new Color(tbox.min(255, r + randomIncrement), g, b));
  		    	break;
  		    case ADD_GREEN:
  		    	if(g < 255)
  		    		shape.setColor(new Color(r, tbox.min(255, g + randomIncrement), b));
  		    	break;
    		case ADD_BLUE:
		    	if(b < 255)
		    		shape.setColor(new Color(r, g, tbox.min(255, b + randomIncrement)));
		    	break;
		    case REM_RED:
  		    	if(r > 0)
  		    		shape.setColor(new Color(tbox.max(0, r - randomIncrement), g, b));
  		    	break;
  		    case REM_GREEN:
  		    	if(g > 0)
  		    		shape.setColor(new Color(r, tbox.max(0, g - randomIncrement), b));
  		    	break;
    		case REM_BLUE:
		    	if(b > 0)
		    		shape.setColor(new Color(r, g, tbox.max(0, b - randomIncrement)));
		    	break;
		    }
    	}
    }   
    
    // change the color of resident shapes to black
    public void turnShapesBlack(){
  	    for(Shape shape : dbox.shapeList){
  	    	shape.setColor(Color.BLACK);
  	    }
    }
    
    // change the color of resident shapes to white
    public void turnShapesWhite(){
  	    for(Shape shape : dbox.shapeList){
  	    	shape.setColor(Color.WHITE);
  	    }
    }
    
	//==============================================================================================
	//==== Shape List Spin Speed Modifiers
    
    // speed up resident shapes' spins
    public void speedupShapesSpin(){
    	int referenceAngleScalar;
  	    for(Shape shape : dbox.shapeList){
  	    	referenceAngleScalar = shape.getReferenceAngleScalar();
  		    if(referenceAngleScalar <= 0){
  			    shape.setReferenceAngleScalar(referenceAngleScalar - 2);
  		    } else {
  			    shape.setReferenceAngleScalar(referenceAngleScalar + 2);
  		    }
  	    }
    } 

    // slow down resident shapes' spins
    public void slowDownShapesSpin(){
    	int referenceAngleScalar;
  	    for(Shape shape : dbox.shapeList){
  	    	referenceAngleScalar = shape.getReferenceAngleScalar();
  		    if(referenceAngleScalar <= 0){
  			    shape.setReferenceAngleScalar(tbox.min(referenceAngleScalar + 2, 0));
  		    } else {
  			    shape.setReferenceAngleScalar(tbox.max(referenceAngleScalar - 2, 0));
  		    }
  	    }
    }
    
	//==============================================================================================
	//==== Animation Pause/Unpause
    
    // pause/unpause
    public void pauseUnpauseAnimation(){
  	    if(pauseToggle){
  		    dbox.frameTimer.stop();
  	    } else {
  		    dbox.frameTimer.start();
  	    }
  	    pauseToggle = !pauseToggle;
    }
    
}


