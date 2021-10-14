/*   file:
 *   FacePamplet.java
 *   simulating social network 
 *   assignment 7/7 cs106a
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;


public class FacePamphlet extends Program implements FacePamphletConstants { 
	
	public static void main(String [] args) {
		new FacePamphlet().start(args);
	}	
	public void run () {
	setSize(800, 500); 
	    currentProfile = dummyProfile;  // Toggle...		
	    canvas = new FacePamphletCanvas();
	    add(canvas);		
	}
	public void init() {	
	    createController();
	    addActionListeners();		
	    facePamphletDatabase = new FacePamphletDatabase();
	    demo = new FacePamphletDemoData();
       }
	// create ui and initialize the database
	public void createController() {
	    // fields
	    nameField = new JTextField(TEXT_FIELD_SIZE);
	    nameField.addActionListener(this); 		
	    changeStatusField = new JTextField(TEXT_FIELD_SIZE);		
	    changeStatusField.addActionListener(this);		
	    changePictureField = new JTextField(TEXT_FIELD_SIZE);
	    changePictureField.addActionListener(this);		
	    addFriendField = new JTextField(TEXT_FIELD_SIZE);
	    addFriendField.addActionListener(this);
	    // buttons		
	    addButton = new JButton("Add");
	    deleteButton = new JButton("Delete");
	    lookupButton = new JButton("Lookup");	
	    changeStatusButton = new JButton("Change Status");		
	    changePictureButton = new JButton("Change Picture");		
	    addFriendButton = new JButton("Add Friend");
		
	    addDemoNames = new JButton("Add Demo Names");
	    addDemoLinks = new JButton("Link Demo Friends");
	    addDemoPics = new JButton("Add Pics to Demo Profiles");
		
	    add(new JLabel("Name") ,NORTH);
	    add(nameField, NORTH);
	    add(addButton, NORTH);
	    add(deleteButton, NORTH);
	    add(lookupButton, NORTH);		
	    add(changeStatusField,WEST);		
	    add(changeStatusButton,WEST);		
	    add(new JLabel(EMPTY_LABEL_TEXT), WEST);
	    add(changePictureField,WEST);
	    add(changePictureButton,WEST);
	    add(new JLabel(EMPTY_LABEL_TEXT), WEST);
	    add(addFriendField, WEST);
	    add(addFriendButton,WEST);
		
	    add(new JLabel(EMPTY_LABEL_TEXT), WEST);
	    add(new JLabel("DEMO DATA"), WEST);
	    add(addDemoNames,WEST);
	    add(addDemoLinks,WEST);
	    add(addDemoPics,WEST);		
	}	
    // Control of the User interface
    public void actionPerformed(ActionEvent e) {		
        Object source = e.getSource();
    	// Add Button
    	if (nameField.getText().isEmpty() != true  && source == addButton) {    			
    	    if (facePamphletDatabase.containsProfile(nameField.getText()) == false) {    				
    	        FacePamphletProfile entry = new FacePamphletProfile(nameField.getText());    				
    		String key = entry.getName();    				
    		facePamphletDatabase.addProfile(key, entry);    				 
    		currentProfile = facePamphletDatabase.getProfile(nameField.getText());    				
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("New Profile Created");
    	    }
            else if (facePamphletDatabase.containsProfile(nameField.getText()) == true) {
    	        canvas.displayProfile(currentProfile);
    		canvas.showMessage("A profile with the name " + nameField.getText() + " already exists");
    	    }
    	}
    	// Delete button 
    	if (nameField.getText().isEmpty() != true && source == deleteButton) {    		
    	    if (facePamphletDatabase.containsProfile(nameField.getText()) == true) {
    	        facePamphletDatabase.deleteProfile(nameField.getText());	    	    
	    	currentProfile = dummyProfile;	    	    
	    	canvas.displayProfile(dummyProfile);
	    	canvas.showMessage("Profile of " + nameField.getText() + " deleted!");
    	    }
            else if (facePamphletDatabase.containsProfile(nameField.getText()) == false) {
                currentProfile = dummyProfile;
        	canvas.displayProfile(currentProfile);
        	canvas.showMessage("A profile with the name " + nameField.getText() + " does NOT exist!");
            }
	}		
    	// Lookup button
    	if (nameField.getText().isEmpty() != true && source == lookupButton) {    
    	    if (facePamphletDatabase.getProfile(nameField.getText()) != null) {
    	        currentProfile = facePamphletDatabase.getProfile(nameField.getText());    			
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("Displaying " + nameField.getText());
    	    }
	    else if (facePamphletDatabase.getProfile(nameField.getText()) == null) {
    	        currentProfile = dummyProfile;
    		canvas.displayProfile(dummyProfile);
    		canvas.showMessage("A profile with the name " + nameField.getText() + " does NOT exist!");
    	    }
    	}
    	// change status button
    	if (source == changeStatusField || source == changeStatusButton) {
    	    if (changeStatusField.getText().isEmpty() != true) {
    	        if (currentProfile != dummyProfile) {    		
    		    // send message to users profile..
    		    currentProfile.setStatus(changeStatusField.getText());    				 
        	    canvas.displayProfile(currentProfile);
        	    canvas.showMessage("Status updated to: " + currentProfile.getStatus());
    		}
    		else if (currentProfile == dummyProfile) {    				
    		    canvas.displayProfile(currentProfile);
    		    canvas.showMessage("Please select a profile to change status!");
    		}
    	    }
    	}
    	// change picture button
    	if (source == changePictureField || source == changePictureButton) {
    	    if (changePictureField.getText().isEmpty() != true) {
    	        if (currentProfile != dummyProfile ) {
    		    // test validity of picture
    		    if (checkPic(changePictureField.getText()) == true) {
    		        currentProfile.setImage(changePictureField.getText());
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Picture Updated!");
		    }
    		    // inform if picture not valid/available
    		    else if (checkPic(changePictureField.getText()) == false) {
    		        canvas.displayProfile(currentProfile);
    			canvas.showMessage("Unable to open file " + changePictureField.getText());
    		    }    				
    		}
    		else if (currentProfile == dummyProfile) {
    		    canvas.displayProfile(dummyProfile);
    		    canvas.showMessage("Please select a profile to change picture!");
    		}
    	    }
    	}
    	// Add Friend button
    	if (source == addFriendField || source == addFriendButton) {    		
    	    if (addFriendField.getText().isEmpty() != true) {
    	        if (currentProfile != dummyProfile) {    			  
    		    if (facePamphletDatabase.containsProfile(addFriendField.getText()) == false) {    				  
    		        canvas.displayProfile(currentProfile);
    			canvas.showMessage("The name you entered, " + addFriendField.getText() + " does NOT exist in Network!");
    		    }
    		    // check that friend prospect is in social network.
    		    else if (facePamphletDatabase.containsProfile(addFriendField.getText()) == true) {      			 
    		        // prevent the adding of CURRENT SUBJECT to CURRENT SUBJECTS FRIENDS list... CANT ADDYOURSELF to YOURFRIENDS!!
    		        if (!currentProfile.getName().equals(addFriendField.getText())) {
    			    // checks done, add friend, then reciprical..
        		     if (currentProfile.addFriend(addFriendField.getText()) == true) { 
                                 // RECIPRICAL FRIEND ADDING!!
        			 FacePamphletProfile recipricalProfile = facePamphletDatabase.getProfile(addFriendField.getText());
        			 if (recipricalProfile.addFriend(currentProfile.getName()) == true) {        					      
        			     canvas.displayProfile(currentProfile);
            			     canvas.showMessage(addFriendField.getText() + " added as a friend!");        					      
        			 }
        			 // in case there is some type of foo foo
        			 else if (recipricalProfile.addFriend(currentProfile.getName()) != true) {
        			     currentProfile = dummyProfile;
        			     canvas.showMessage("There was a Problem adding friends");
        			 }
        	             }
        		     // if friend is ALREADY in current's list...
        	             else if (currentProfile.addFriend(addFriendField.getText()) == false) {
        	                 canvas.displayProfile(currentProfile);
        	                 canvas.showMessage(addFriendField.getText() + " is ALREADY on friendlist!");        				      
        		     }
    		        }
			// Don't add yourself to your OWN friendLIST
    			else if (currentProfile.getName().equals(addFriendField.getText()))  {    
    			    canvas.displayProfile(currentProfile);
    			    canvas.showMessage("Adding yourself to friendList is redundant!!");    					  
			}
    		    }
                }
    		else if (currentProfile == dummyProfile) {    		      
    		    canvas.displayProfile(currentProfile);
    		    canvas.showMessage("Please select a valid profile to add friend!");    			  
    		}
    	    }    			
    	}
    	// test data names..
    	if ( source == addDemoNames) {   // load profile names, no pics...
    	    if (facePamphletDatabase.containsProfile("moe")) {    			
    	        currentProfile = dummyProfile;
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("DEMO NAMES PREVIOUSLY LOADED!!");
    	    }
    	    else if (!facePamphletDatabase.containsProfile("moe")) {    			
    	        demo.addDemoPeople(facePamphletDatabase);
    	        currentProfile = dummyProfile;
    		canvas.displayProfile(currentProfile);
        	canvas.showMessage("SUCCESS DEMO PROFILES LOAD COMPLETE! (no Pics)");	
    	    }    		
    	}	    	
    	// test data links..
    	if (source == addDemoLinks) {   // link in accordance...    		
    	    // check to make sure the profiles(no pics) loaded
    	    if (facePamphletDatabase.containsProfile("moe")) {
    	        // check to make sure that the friend links are not established.
    		Iterator<String> bIt = facePamphletDatabase.getProfile("moe").getFriends();
    		if (bIt.hasNext()) {
		    currentProfile = dummyProfile;
    		    canvas.displayProfile(dummyProfile);
    	    	    canvas.showMessage("DEMO FRIEND LINKS PREVIOUSLY DONE!");
    	    	    // this could be a bug if manual interface first, at least with moe.
    		}
    		else {
    		    // if the iterator has no elements, assume the pre-links have not happened.    				
    		    demo.linkFriends(facePamphletDatabase);
    		    currentProfile = dummyProfile;
    		    canvas.displayProfile(dummyProfile);
    	    	    canvas.showMessage("SUCCESS DEMO FRIEND LINKS COMPLETE!");
    		}
    	    }	
    	    else if (!facePamphletDatabase.containsProfile("moe")) {
    	        currentProfile = dummyProfile;
		canvas.displayProfile(dummyProfile);
	    	canvas.showMessage("NO PROFILES TO LINK, ADD DEMO NAMES First!");    		    
    	    }    			  			
    	}
    	// add pics to test/demo profiles..
    	if (source == addDemoPics) {
    	    if (facePamphletDatabase.containsProfile("moe")) {
    	        if (facePamphletDatabase.getProfile("moe").getImage() == null) {    				
    		    if (demo.addDemoPics(facePamphletDatabase)) {    					
    		        currentProfile = dummyProfile;
    			canvas.displayProfile(dummyProfile);
    		    	canvas.showMessage("PICTURES SUCCESSFULLY added FOR DEMO's and PICTURES!");
    		    }    				
    		    else if (demo.addDemoPics(facePamphletDatabase) == false) {
    		        currentProfile = dummyProfile;
    			canvas.displayProfile(dummyProfile);
    		    	canvas.showMessage("THERE IS A PROBLEM WITH THE JPG(S)!");
    		    }
    		}    			
    		else if (facePamphletDatabase.getProfile("moe").getImage() != null) {    				
    		    currentProfile = dummyProfile;
    		    canvas.displayProfile(dummyProfile);
    	    	    canvas.showMessage("PICTURES PREVIOUSLY LOADED!");
    		}    			
    	    }
    	    // if no moe, no 1st script..., no test profiles....
    	    else if (!facePamphletDatabase.containsProfile("moe")) {    			
    	        currentProfile = dummyProfile;
		canvas.displayProfile(dummyProfile);
	    	canvas.showMessage("NO PROFILES TO ADD PIC!, RUN 1st script First!");    			
    	    }
    	}
    }  // end of action performed...    
    // checking the image out here as well!!
    private boolean checkPic(String inFile) { 		
        try { 		    
 	    testImage = new GImage(inFile); 			
 	} 
 	catch (ErrorException ex) { 			
            new ErrorException(ex);			
 	    return false;
 	 }
 	return true; 		
    }
    // instance variable
    private JTextField nameField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton lookupButton;
    private JTextField changeStatusField;
    private JTextField changePictureField;
    private JTextField addFriendField;    
    private JButton changeStatusButton;
    private JButton changePictureButton;
    private JButton addFriendButton;
    private GImage testImage;
    
    private JButton addDemoNames; 
    private JButton addDemoLinks;
    private JButton addDemoPics;
    //..    
    private FacePamphletDatabase facePamphletDatabase; 
    private FacePamphletProfile currentProfile;
    private FacePamphletProfile dummyProfile  = new FacePamphletProfile("() No Current Profile"); // never inserted into the database!
    private FacePamphletCanvas canvas; 
    private FacePamphletDemoData demo;
}
