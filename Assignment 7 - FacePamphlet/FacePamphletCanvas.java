/* File: FacePamphletCanvas.java
 * This class represents the canvas on which the profiles ls in the social
 * network are displayed.  Note: within the realm of the assignment, the
 * entire screen is NOT redisplayed, so do at own risk!
 */
import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {
	
	//  Constructor	 
	public FacePamphletCanvas() {
		// Taking care of configuration stuff elsewhere...
	}	
	/* Display the passed profile on the canvas. The canvas is first cleared of all existing 
	 * items(including messages displayed near the bottom of the screen) and then the given profile
	 * is displayed.  The profile display includes the name of the user from the 
	 * profile, the corresponding image (or an indication that an image does not exist), 
	 * the status of the user, and a list of the user's friends in the social network
	 */
	public void displayProfile(FacePamphletProfile inProfile) {		
		removeAll();
		addParts(inProfile);				
	}	
	/* Displays a message string near the bottom of the canvas.  Every time this method 
	 * is called, the previously displayed message (if any) is replaced by the new 
	 * message text passed in.
	 */
	public void showMessage(String inMsg) {		
		GLabel messageStatusLabel = new GLabel(inMsg);
		messageStatusLabel.setFont(MESSAGE_FONT);
		add(messageStatusLabel, (getWidth() / 2) - (messageStatusLabel.getWidth() / 2), getHeight() - BOTTOM_MESSAGE_MARGIN);		
	}	
	// helper called by method in THIS class to help display profile
	private void addParts(FacePamphletProfile inProfile) {
		//label..
		GLabel nameLabel = new GLabel(inProfile.getName());
	    nameLabel.setFont(PROFILE_NAME_FONT);				
		nameLabel.setColor(Color.BLUE);
		add(nameLabel, LEFT_MARGIN, TOP_MARGIN + nameLabel.getAscent());		  
	    // photograph		
		// If profile has pic and valid profile 	
		if (inProfile.getImage() == null && inProfile.getName() == inProfile.getName()) {
			//System.out.println("No previous pic...");
			GRect imageRect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);			
			add(imageRect, LEFT_MARGIN, (nameLabel.getDescent() + IMAGE_MARGIN * 3));			 
			GLabel imageLabel = new GLabel("NO IMAGE");
			imageLabel.setFont(PROFILE_IMAGE_FONT);				
			double x = (imageRect.getWidth() / 2 - imageLabel.getWidth() + imageLabel.getWidth() / 2 + imageRect.getX());
			double y = imageRect.getY() + (imageRect.getHeight() / 2);  // centering required...				
			add(imageLabel,x, y);			
			y2 = imageRect.getY(); // labels and remaining graphics coded to leverage off of image's y coordinate.			
		}		
		// if profile has a picture
		else if (inProfile.getImage() != null) {			
	        GImage profileImage = inProfile.getImage();	        
	        profileImage.setBounds(LEFT_MARGIN, (nameLabel.getDescent() + IMAGE_MARGIN * 3), IMAGE_WIDTH,IMAGE_HEIGHT);    
	        add(profileImage);
	        y2 = profileImage.getY(); // labels and subsequent graphics coded to leverage off of image's y coordinate.
		}		
		// get the status string from profile class... 
		GLabel statusLabel = new GLabel(inProfile.getStatus());
		statusLabel.setFont(PROFILE_STATUS_FONT);		
		add(statusLabel, LEFT_MARGIN, y2 + STATUS_MARGIN * 2 + IMAGE_HEIGHT);		
		// friends
		GLabel friendLabel = new GLabel("Friends");
		friendLabel.setFont(PROFILE_FRIEND_LABEL_FONT);		
		add(friendLabel, getWidth() / 2, y2);		
		double friendYcoord = y2 + STATUS_MARGIN;		
		Iterator<String> bIt = inProfile.getFriends(); // create/call iterator    		
		while (bIt.hasNext()) {			   
			 String tStr = bIt.next();
			 GLabel friendString = new GLabel(tStr);
			 friendString.setFont(PROFILE_FRIEND_FONT);			 
			 add(friendString, getWidth() / 2, friendYcoord);			 
			 friendYcoord = friendYcoord + 20;  // increment for location			  
		}
	}	
	private double y2;	
}

