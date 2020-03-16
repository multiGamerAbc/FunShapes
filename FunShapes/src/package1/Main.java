package package1;

public class Main {

	//==============================================================================================
	//==== Main Function
	
	// instantiate DataBox, bring up animation and interface panels
	public static void main(String[] args) {
		DataBox dbox = new DataBox();
		new AnimationPanel(dbox);
		new ButtonPanel(dbox);		
	}
}


////////////////////////////////////////////////////////////////////////////////////////////////////
//---- To Do ----//

// use a Shape superclass, not interface
// decrease timer delay
// replace max and min?
// initialize data box (Comment)
// constants which indicate a shape (for ....
// ints vs enums in dbox
// move anything that's used in only one place out of dbox and into that place
// refactor
// sort categories
// buttonpanel name
// use searches to check the categorizations in DataBox, etc
// numDisplayPanel spagetti
// button group toggle indicator
// highlight menu when moused over
// click to pause
// test on different monitors
// add demo menu
// check all window configurations
// static vs non-static tbox in menuItemHandler
// startNewAnimationFunction argument necessary?
// DataBox initially configure windows
// configure windows in data box or in tool box
// end of file space
//think about encapsulation a little more

//------------

// spin issue, <= in speed spinner
// spin speedup/slowdown bug
