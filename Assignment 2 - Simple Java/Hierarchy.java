/* file Harchy.java
 * Write a GraphicsProgram subclass that draws a partial diagram
 * of the acm.program hierarchy.
 * 
 */

import acm.program.*;
import java.awt.*;
import acm.graphics.*;

public class Hierarchy extends GraphicsProgram {
	
	public static void main(String [] args) {
	    new Hierarchy().start(args);
	}	
	public void run () {
	    createBoxes();
	}	
	// create boxes creates 4 grects, does the 
	// centering calculations and adds the glines
	private void createBoxes() {		
	    // variables below assist with centering and locations.
	    int boxSpace = 25;
	    int boxNspace = (boxWidth * 3 + boxSpace * 2);
	    int totBoxHeight = boxHeight * 3;
	    int totalSparePix = getHeight() - totBoxHeight;
	    int topBotBuff = totalSparePix / 2;
	    int botOfBoxY = getHeight() - topBotBuff;
	    int botBoxYcoord = botOfBoxY - boxHeight;
		
	    // create top box		
	    GRect rect1 = new GRect((getWidth() - boxWidth) / 2, (getHeight() - (boxHeight * 3)) / 2, boxWidth, boxHeight);		
	    add(rect1);		
			    	
	    //Create 2nd row of boxes AFTER a box sized space...
	    int box2x = (getWidth() - boxNspace) / 2;  // calculation for x coordinate
	    GRect tRect2 = new GRect((getWidth() - boxNspace) / 2, botBoxYcoord, boxWidth, boxHeight);		
	    add(tRect2);	 
				
	    int box3x = box2x + boxWidth + boxSpace;	// calculation for x coordinate	
	    GRect tRect3 = new GRect(box3x, botBoxYcoord, boxWidth, boxHeight);		
	    add(tRect3);		
				
	    int box4x = box3x + boxWidth + boxSpace;	// calculation for x coordinate
	    GRect tRect4 = new GRect(box4x, botBoxYcoord, boxWidth, boxHeight);
	    add(tRect4);
		
	    // connect top box with bottom left box
	    GLine tLine = new GLine(rect1.getX() + boxWidth /2, rect1.getY() + boxHeight, tRect2.getX() + boxWidth /2 , tRect2.getY());
	    add(tLine);		 
			
	    // connect top box with bottom middle box	
	    GLine tLine2 = new GLine(rect1.getX() + boxWidth /2, rect1.getY() + boxHeight, tRect3.getX() + boxWidth /2, tRect3.getY());
	    add(tLine2);
		
	    // connect top box with bottom right box
	    GLine tLine3 = new GLine(rect1.getX() + boxWidth /2, rect1.getY() + boxHeight, tRect4.getX() + boxWidth /2, tRect4.getY());
	    add(tLine3);		
		
	    // Create label for top box
	    // Do NOT add coordinates during initial creation of label...  Need object created to leverage
	    // off of size of label to create the correct coordinates...
	    // also note, taking the default font..../size.
	    GLabel pLabel = new GLabel("Program");                                   		
            pLabel.setColor(Color.BLUE);
            // notice how the coordinates were calculated. It's apparent by looking at the command...
            add(pLabel, rect1.getX() + (boxWidth - pLabel.getWidth())  / 2, rect1.getY() + (boxHeight - pLabel.getHeight()) /2 + pLabel.getAscent());
        
            // graphics program label
            GLabel  gLabel = new GLabel("GraphicsProgram");
            add(gLabel, tRect2.getX() + (boxWidth - gLabel.getWidth()) / 2, tRect2.getY() + (boxHeight - gLabel.getHeight()) / 2 + gLabel.getAscent());
        
            // console program label
            GLabel cLabel = new GLabel("ConsoleProgram");
            add(cLabel, tRect3.getX() + (boxWidth - cLabel.getWidth()) / 2, tRect3.getY() + (boxHeight - cLabel.getHeight()) / 2 + cLabel.getAscent());
        
            // dialog program label
            GLabel dLabel = new GLabel("DialogProgram");
            add(dLabel, tRect4.getX() + (boxWidth - dLabel.getWidth()) / 2, tRect4.getY() + (boxHeight - dLabel.getHeight()) / 2 + dLabel.getAscent());
	}
		
	// static variables
	private static final int boxHeight = 50;  
	private static final int boxWidth = 114;
}
