package package1;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class AnimationPanel extends JPanel implements ActionListener{	

	//==============================================================================================
	//==== Data
	
	DataBox dbox;
	Toolbox tbox;
	
	//==============================================================================================
	//==== Constructor
    
	public AnimationPanel(DataBox dbox) {
		this.dbox = dbox;
		tbox = new Toolbox(dbox);
   		JFrame animationPanelWindow = new JFrame("Bouncing Shapes");
		setBackground(dbox.animationPanelBackgroundColor);
		requestFocusInWindow(); 
		setPreferredSize( dbox.animationPreferredSize );
		animationPanelWindow.setContentPane(this);
		animationPanelWindow.pack();
		animationPanelWindow.setLocation(dbox.animationPanelLeftEdge, dbox.animationPanelTopEdge);
		animationPanelWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		animationPanelWindow.setResizable(true);
		animationPanelWindow.setVisible(true);
   		dbox.frameTimer =  new Timer(dbox.timerDelay, this);  // Timer object to drive animation
		dbox.frameTimer.start();
	}
	
	//==============================================================================================
	//==== Painting and Timer Handling
	
    protected void paintComponent(Graphics g) {
    	// call the super
    	super.paintComponent(g);
    	// draw and update coordinates for all squares
		for(Shape shape : dbox.shapeList){
			shape.updateAndDraw(g);
  		}
    }
	
	// Handles ActionEvents from Timer object to drive animation.
    public void actionPerformed(ActionEvent e){
    	// perform wall test and update reference angle and x and y offsets for each square
    	for(Shape shape : dbox.shapeList){
    		tbox.wallTest(shape);
    		shape.positionUpdate();
    	}
        repaint();
    }    	
}


