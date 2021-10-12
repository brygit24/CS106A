/* File: HailStone.java
 * 
 * Write a consoleprogram that reads in a number from the user and
 * then displays the hailstone sequence for that number, just as in
 * Hofstadtlers book, followed by a line that indicates the number of
 * steps taken to reach 1.
 * 
 */

import acm.program.*;

public class HailStone extends ConsoleProgram {
	
	public static void main(String [] args) {
		new HailStone().start(args);
	}
	
	// variables...
	int workInt;
	int sentinal = 1;	
	int tryCount = 0;
	
	public void run () {		
	    
		int n = readInt("Enter a number..");
		workInt = n;
		
	    while (true) {
	    	tryCount++;
		    if (workInt % 2 == 1) {   	
		    	println(workInt + " is odd, so I make 3n + 1: " + (workInt * 3 + 1));
			    workInt = workInt * 3 + 1;
			    if (workInt == sentinal) {
			    	break;
			    }
			}
		    else 
		    {
			    println (workInt + " is even so, I take half: " + workInt / 2);
			    workInt = workInt / 2;			    
				if (workInt == sentinal) {
				    break;
				}
			} 
	   }	
	   println("This process took " + tryCount + " to reach " + workInt);
	}
}
