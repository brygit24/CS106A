
/* file:  Target.java
 * Write a graphics program that creates a red and white targetenters a target in the 
 * in the center of the initial screen. * 
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class Target extends GraphicsProgram {
    
	public static void main(String [] args) {
	    new Target().start(args);
	}
    
    public void run() {
		GOval tOval = new GOval(30, 50, 50, 50);
		tOval.setFilled(true);
		tOval.setFillColor(Color.RED);		
		
		GOval tOval2 = new GOval(40, 60, 30,30);
		tOval2.setFilled(true);
		tOval2.setFillColor(Color.WHITE);		
		
		GOval tOval3 = new GOval(50, 70, 10,10);
		tOval3.setFilled(true);
		tOval3.setFillColor(Color.RED);
				
		GOval tOval8 = new GOval(165,165, 70, 70);
		tOval8.setFilled(true);
		tOval8.setFillColor(Color.red);		
		
		GOval tOval7 = new GOval(175,175, 50, 50);
        tOval7.setFilled(true);
        tOval7.setColor(Color.white); 
		
		GOval tOval6 = new GOval(185,185, 30, 30);
		tOval6.setFilled(true);
		tOval6.setFillColor(Color.red);
		
		// instance variables
		
		int cxx = getWidth() /2;
		int cyy = getHeight()/2;
		int diam = 30;
		int radius = diam / 2;
		
		int r1 = 72;
		int r2 = 52; 
		int r3 = 28; 		
		
		GOval tRect1a = new GOval(cxx - r1, cyy - r1, r1 * 2, r1 * 2);
		tRect1a.setFilled(true);
		tRect1a.setFillColor(Color.red);
		add(tRect1a);
		
		GOval tRect1b = new GOval(cxx - r2, cyy - r2, r2 *2 , r2 * 2);
		tRect1b.setFilled(true);
		tRect1b.setFillColor(Color.white);
		add(tRect1b);

		GOval tRect1c = new GOval(cxx - r3, cyy - r3, r3 * 2, r3 *2);
		tRect1c.setFilled(true);
		tRect1c.setFillColor(Color.red);
		add(tRect1c);		
	}
	
}
