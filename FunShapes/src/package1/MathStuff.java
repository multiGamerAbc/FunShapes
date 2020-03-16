package package1;

public class MathStuff{
	
	//==============================================================================================
	//==== Math Functions

	// used for animation randomization effects
    public double randomizingScalar(double x){
    	if(x < 0){
    		return 0;
    	} else if(x < .05){
    		return -6*x + 1;
    	} else if(x < .45){
    		return -x + .75;
    	} else if(x < .50){
    		return -6*x + 3;
    	} else if(x < .55){
    		return 6*x - 3;
    	} else if(x < .95){
    		return x - .25;
    	} else if(x < 1.0){
    		return 6*x - 5;
    	} else {
    		return 0;
    	}
    }
}


