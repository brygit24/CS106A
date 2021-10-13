/* file  Pythagoreantheorem.java
 * Write a console program that accepts values for a and b
 * as ints and then calculates the solution of c as a double
 * This is the solution for the high school geometry theorom 
 *  "Pythagorean's theorem"  which is in regard to the relationship
 *  of the lengths of the 3 sides of a right triangle.
 *  ie: if value a = 6 and b = 11 the double would be: 12.52926....
 * 
 */

import acm.program.*;


public class Pythagoreantheorem extends ConsoleProgram {
	
	public static void main(String [] args) {
		new Pythagoreantheorem().start(args);
	}	
	public void run () {
	    println("Enter values to compute Pythagorean's theorom");
  	    int int1 = readInt("a:");
  	    int int1R = int1 * int1;
  	    int int2 = readInt("b:");
  	    int int2R = int2 * int2;
  	    int int3 = int1R + int2R;      	  
  	    double yy = Math.sqrt(int3);
  	    println("c = "+ yy);	
	}
}
