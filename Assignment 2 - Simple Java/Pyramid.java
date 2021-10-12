
/** Pyramid.java
 * write a graphics sub class program creating a pyramid consisting of bricks arranged in horizontal
 * rows, so that the number of bricks in each DECREASES by ONE as you MOVE up the pyramid.
 * 
 *     H-LEVEL
 *     * hidden assumptions.. 
 *       - Centering of the bricks is incumbent on the ECLIPSE applet starting window size.
 *         For this, I set the RUN configuration parameters to 400 / 400.  If not, brick arrangement 
 *         is not centered...
 *       - NOT checking for things like too many base bricks, bounds etc...
 *     
 *     * I/O
 *       - input = static variables for bricks..
 *       - output = a pyramid each row of bricks decreasing by 1 as it ascends...
 *       
 *     * Approach:
 *       - data structure:
 *         - variables are integers
 *         - Grects are the bricks
 *         
 *       - Mutation, data
 *         - Grect loop to create brick(s)
 *         - getwidth to assist with centering..
 *         - decrement brick count
 *         - ascend as creation of the pyramid 
 *                  
 *       - create the initial row of bricks centered in accordance with
 *         - base of brick
 *         - brick size for statics variables
 *         - centered on the bottom row of the screen
 *         
 *       - decrease the number of bricks by 1, center the bricks on the next ascending row
 *         - use a control statement
 *         - decrement the brick count
 *         - ensure that the bricks are centered
 *         
 *       - repeat until there are no more bricks... (no boundry testing..)
 *        *       
 *    
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
	
	// PUBLIC STATIC VARIABLES 
	
	// width of each brick in pixels.
	public static final int BRICK_WIDTH = 25;	
	// height of each brick in pixels.
	public static final int BRICK_HEIGHT = 15;	
	// number of bricks in the base of the pyrimid
	public static final int BRICKS_IN_BASE = 15;		
}

