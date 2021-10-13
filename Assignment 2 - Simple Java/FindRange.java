/* File:  FindRange.java
 * Write a consoleprogram that reads in a list of integers, one
 * line at a time until a sentinel value of 0(or whatever) is
 * entered. When the sentinel is read, the program should display
 * the largest and smallest values of the list.
 * 
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	public static void main(String [] args) {
	    new FindRange().start(args);
	}
	public void run () {		
	    int intCount = 0;
	    int smallest = 0;
	    int largest = 0;
		
	    println("This program finds the largest and smallest numbers");
	    println("When you are done entering integers, enter 0 to exit.");		
		
	    while (true) {			
	        int n1 = readInt("? ");			
		if (n1 == 0 && intCount == 0) {				
		    break;
		}				
		if (n1 == 0 && intCount == 1) {				
		    break;
		}
		if (n1 == 0) {				
		    break;			
		}
		else {
		    intCount++;				
		    if (intCount == 1) {
		        smallest = n1;
			largest = n1;
		    }
		    if (n1 < smallest) {
		        smallest = n1;
		    }
		    if (n1 > largest) {
		        largest = n1;
		    }				
		}			
	    }
	    if (intCount == 0) {
	        println("You did NOT enter any values!!");
	    }
	    if (intCount == 1) {
	        println("one entry " + largest + " Are both, lowest and smallest " + smallest);
	    }
	    else if (intCount > 1) {
	        println("Smallest: " + smallest);
		println("Largest: " + largest);	
	    }					
	}     
}
		

