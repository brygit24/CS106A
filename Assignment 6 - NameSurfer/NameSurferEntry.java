/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

public class NameSurferEntry implements NameSurferConstants {
    /* Constructor: NameSurferEntry(line)
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file. Each line begins with the name, which is
     * followed by integers giving the rank of that name for each 
     * decade.
     */    
    public NameSurferEntry(String inLine) {	
        int nameEnd = inLine.indexOf(" ");		
	name = inLine.substring(0, nameEnd); // going with the 0 hardcoded, we know the 1st char is a char...		
	name = name.toLowerCase();		
	rankString = inLine.substring(nameEnd);
	rankString = rankString.trim(); // get rid of starting and trailing spaces!!!				
    }    
    // return the name associated with this entry which,
    // is set in the constructor...
    public String getName() {		
        return name;
    }    
    // Called from surfer graph to assist with graphing this entries rank
    // I created the rankstring in the constructor
    public String getRank() {		
        return rankString;
    }	
    /* toString, this is an over-ride of the default method.
     * Modified to return the string in accordance with specs.
     * For this program, need a string of the name and the rank	 
    */
    public String toString() {		
        return (name + " [" + rankString + "]");  // according to spec...
    }	
    // instance variable
    private String name, rankString;	
}
