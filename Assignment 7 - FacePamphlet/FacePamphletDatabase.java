/*
 * file: FacePamphletDatabase.java
 * This class keeps track of the profiles of all users in the 
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same folk.
 */

import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {
	// Constructor
	public FacePamphletDatabase() {
		// no definitions for now, if needed will add..
	}
	/*
	 * This method adds the given profile to the database.  If the name
	 * associated with the profile is the same as an existing name
	 * in the database, user recieves message that profile is already in existence
	 */
	public void addProfile(String inName, FacePamphletProfile inProfile) {     	
		facePamphletDatabase.put(inName, inProfile);  // database engine type is default/built into java...  A HASHMAP of FP data types!!
	}	
    /* This method returns the profile associated with the given name
     * in the database.  If there is no profile in the database with 
     * the given name, the method returns null
    */	
	public FacePamphletProfile getProfile (String inName) {		
	    return facePamphletDatabase.get(inName);
	}	
	/*
	 * This method removes the profile associated with the given name from the 
	 * database. It also updates the list of friends of all other profiles in the
	 * database to make sure that this name is removed from the list of friends of any other
	 * profile. 
	 * if there is no profile in the database with the given name, then the database
	 * is unchanged after calling this method.
	 */
	public void deleteProfile(String inName) {
		// note: no need to check for existing entry or existence since a "contains" was ran in prior to this call.
		// we need to check for friends list		
		// need a FP type to work with..
		FacePamphletProfile workProfileIn = facePamphletDatabase.get(inName);
		// incoming persons list iterator
		Iterator<String> bIt = workProfileIn.getFriends();    		
		while (bIt.hasNext()) {			   
			 String tStr = bIt.next();			  
			 FacePamphletProfile workProfileRecip = facePamphletDatabase.get(tStr);
			 workProfileRecip.removeFriend(inName);			 
			 // was a safety net...
			 /*   if (workProfileRecip.removeFriend(inName) == true) {
			  System.out.println("success"); // leave for now
			 }
			 else if (workProfileRecip.removeFriend(inName) != true ) {
			  System.out.println("foo!!"); // leave for now
			 }
			 */			 
		}
		facePamphletDatabase.remove(inName); 
   }	
	/*
	 * This method returns true if there is a profile in the database that has the
	 * given name.  It returns false otherwise
	 */
	public boolean containsProfile(String inName) {		
		if (facePamphletDatabase.size() == 0) {  			
			return false;
		}
		else if (facePamphletDatabase.size() != 0) {					
		    for (String key : facePamphletDatabase.keySet() ) {    
		        if (key.equals(inName)) { // remember diff str.equals() .vs str1 == str2 == boo boo!! in this case!! check others!!			        
				    return true;
			    }		        
		    }
		}
		return false; 
	}
	// inventory of all profiles...	
	private HashMap<String, FacePamphletProfile> facePamphletDatabase = new HashMap<String, FacePamphletProfile>();
}

