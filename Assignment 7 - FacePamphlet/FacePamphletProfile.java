/*
 * File: FacePamphletProfile.java
 * --- This class keeps track of all the information for one profile
 *     in the FacePamphlet social network.  Each profile contains a 
 *     name, an image(which may not always be set), a status (what the
 *     person is currently doing, which may not always be set), and
 *     a list of friends.
 */

import acm.graphics.*;
import java.util.*;
import acm.util.*;

public class FacePamphletProfile implements FacePamphletConstants { 
	// Constructor 	
	public FacePamphletProfile(String inName) {		
	    profileName = inName;
	    profStatus = "No Current Status";
	    profImage = null;		
	}	
	/*
	 * This method returns the name associated with the profile
	 */
	public String getName() {
	    return profileName;
	}	
	/*
	 * The method returns the image associated with the profile
	 * If there is no image associated with the profile, the method returns null..
	 */
	public GImage getImage() {		
	    return profImage;
	}
	/*
	 * This method sets the image associated with the profile
	 * Note, double protection is coded in with regard to getting
	 * a foo image.  I test it in facepamphlet as well..
	 */	
	public boolean setImage(String inFile) {	
	    try {		    
	        profImage = new GImage(inFile);
		return true;
	    } 
	    catch (ErrorException ex) {			
	        profImage = null;			
		//throw new ErrorException(ex);
		return false;			
	    }		
	}
	/*
	 * This method returns the status associated with the profile.
	 * If there is no status associated with the profile, the method
	 * returns an empty string("");
	 */
	public String getStatus() {		
	    return profStatus;
	}
	/*
	 * This method sets the status associated with the profile
	 */
	public void setStatus(String inStatus) {
	    profStatus = inStatus;
	}	
	/*
	 * This method adds the named friend to the profiles list of 
	 * friends.  It returns true if the friends name was not already
	 * in the list of friends for this profile (and the name is added to the
	 * list). The method returns false of the given friend name was already 
	 * in the list of friends for this profile (in which case, the given
	 * friend name is not added to the list of friends a second time.)
	 */	
	public boolean addFriend(String inFriend) {
	    if (friendsList.size() == 0) {  // improve log time???
	        friendsList.add(inFriend);			
		return true;
	    }
	    // use a search helper, if false add and split
	    else if (checkFriendList(inFriend) == false ) {
	        friendsList.add(inFriend);
		return true;
	    }	    
	    return false;		
	}	
	// a search helper to assist with addFriend check	
	private boolean checkFriendList(String inFriend) {
	    for (String element: friendsList) {
	        if (element.contains(inFriend)) {				
		    return true;
		}
	    }
	    return false;
	}
	/* This method removes the named friend from this profile's list
	 * of friends.  It returns true if the friend's name was in the 
	 * list of friends for this profile (and the name was removed
	 * from the list).  The method returns false if the given friend name
	 * was not in the list of friends for this profile. (in which
	 * case, the given friend name could not be removed).
	 */
	public boolean removeFriend(String inFriend) {		
	    for (String element : friendsList) {
	        if (element.contains(inFriend)) {
		    friendsList.remove(element);
		    return true;
		}
	    }
	    return false;
	}	
	/*
	 * this method returns an iterator over the list of friends
	 * associated with the profile.
	 */
	public Iterator<String> getFriends() {	
	    return friendsList.iterator();		
	}
	/*
	 * This method returns a string representation of the profile.
	 * This string is in the form: "name (status): list of friends",
	 * where name and status are set accordingly and the list of friends
	 * is a comma separated list of names of all of the friends in this profile,
	 * overrides default toString method
	 */
	public String toString() {		
	    if (friendsList.size() == 0) {			
	        return (profileName + " (" + profStatus + "): " );
	    }
	    else if (friendsList.size() > 0 ) {
	        fList = getFriendString();
	    }		
	    return (profileName  + " (" + profStatus + "): " + fList);	
	}
	// method to create a comma separated list of friends for this profile...
	private String getFriendString()  {
	    String outStr = "";
	    Iterator bIter = friendsList.iterator();		
    	    while (bIter.hasNext()) {
    	        outStr = outStr + bIter.next() + ", ";
	    }
    	    outStr = outStr.substring(0, outStr.length() - 2);
    	    return outStr;
	}	
	// instance variables
	private String profileName;	
	private String fList;
	private String profStatus;
	private GImage profImage;	
	private ArrayList<String> friendsList = new ArrayList<String>();
}
