
/** file: Pyramid.java
 * write a graphics sub class program creating a pyramid consisting of bricks arranged in horizontal
 * rows, so that the number of bricks in each DECREASES by ONE as you MOVE up the pyramid.
 *  
 */

import acm.program.*;
import acm.graphics.*;

public class Pyramid extends GraphicsProgram {		
	
	public static void main(String [] args) {
		new Pyramid().start(args);
	}	
	public void run () {		
	    int yCoord = getHeight() - BRICK_HEIGHT;  // VERTICAL coordinate.. INITIATOR...start window height - brick height()			
	    for (int lBase = BRICKS_IN_BASE; lBase > 0; lBase--) {  // using "lBase" as inital brick count..  	
	        int xCoord = (getWidth() - (lBase * BRICK_WIDTH)) / 2; // Horizontal coordinate.. INITIATOR..width - total brick length then half.
	    	putRow(xCoord, lBase, yCoord);	  // call the row/brick laying method
	    	yCoord = yCoord - BRICK_HEIGHT;	    	
	    }
	}	
	// lay a row of bricks. params are the x coordinate, # of bricks to lay and the y coordinate
	private void putRow(int xCoord2, int inBase, int yCoord2) {		
	    for (int i = 0; i < inBase; i++) {	// at the # of bricks in accordance with inBase/# of bricks		
	        GRect tRect = new GRect(xCoord2, yCoord2, BRICK_WIDTH, BRICK_HEIGHT);
		add(tRect);
		xCoord2 = xCoord2 + BRICK_WIDTH;// change the x coordinate. Add the length of a brick...			
	    }
	}
	// width of each brick in pixels.
	private static final int BRICK_WIDTH = 25;	
	// height of each brick in pixels.
	private static final int BRICK_HEIGHT = 15;	
	// number of bricks in the base of the pyrimid
	private static final int BRICKS_IN_BASE = 15;		
}

