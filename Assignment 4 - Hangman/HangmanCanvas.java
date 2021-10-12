/* file: HangmanCanvas.java
 * hangManCanvas.java, the graphical aspect of assignment 4, hangman.
 * it keeps track of the hangman display  
 */



import acm.graphics.*;
import acm.program.*;

public class HangmanCanvas extends GCanvas {
	
	// ** Resets the display so that only the scaffold appears 
	public void reset() {
		if (getElementCount() > 1) {
			removeAll();			
		}
		createScaffold();		
		tLabel1= new GLabel("", getWidth() / 5, getHeight() - 130);
		add(tLabel1);
		
		tLabel2= new GLabel("", getWidth() / 5, getHeight() - 100);
		add(tLabel2);		
	}	
	/*
	 * Updates the word on the screen to correspond to the current state of the game.
	 * The argument string shows what letters have been guessed so far; unguessed letters
	 * appear as hyphens -- like in console version/side
	 */	
	public void displayWord(String inWord) {       
    tLabel1.setLabel(inWord);
    tLabel1.setFont("SansSerif-25");		
	}	
	/*
	 * Updates the display to correspond to an incorrect guess by the user.  Calling this method
	 * causes the next body part to appear on the scaffold and adds the letter to the
	 * list of incorrect guesses. (new)...that appear at the bottom of the graphics window.
	 */
	public void noteIncorrectGuess(String inWord, int inCount) {		
		tLabel2.setLabel(inWord);		
		if (inCount == 7) {
			GOval head = new GOval(getWidth() / 2 - (HEAD_RADIUS) / 2, getHeight() / 4 + ROPE_LENGTH, HEAD_RADIUS, HEAD_RADIUS);
			add(head);	
		}
		else if (inCount == 6) {			
			GLine body = new GLine(getWidth() /2, getHeight() / 4 + ROPE_LENGTH + HEAD_RADIUS, getWidth() / 2, getHeight() / 4 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH );
			add(body);
		}
		else if (inCount == 5) {			
			GLine leftUpperArm = new GLine( getWidth() /2, getHeight() / 4 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, 
					getWidth() /2 - UPPER_ARM_LENGTH, getHeight() / 4 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD); 
			add(leftUpperArm);
			
			GLine leftLowerArm = new GLine(getWidth() /2 - UPPER_ARM_LENGTH, getHeight() / 4 + (ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD), 
					getWidth() /2 - UPPER_ARM_LENGTH, getHeight() / 4 + HEAD_RADIUS + ROPE_LENGTH + LOWER_ARM_LENGTH);			
			add(leftLowerArm);						
		}
		else if (inCount == 4) {			
			GLine rightUpperArm = new GLine( getWidth() /2, getHeight() / 4 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, 
					getWidth() /2 + UPPER_ARM_LENGTH,getHeight() / 4 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD); 
			add(rightUpperArm);		
			GLine rightLowerArm = new GLine(getWidth() /2 + UPPER_ARM_LENGTH, getHeight() / 4 + (ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD), 
					getWidth() /2 + UPPER_ARM_LENGTH, getHeight() / 4 + HEAD_RADIUS + ROPE_LENGTH + LOWER_ARM_LENGTH);			
			add(rightLowerArm);			
		}
		else if (inCount == 3) {
			GLine leftHip = new GLine(getWidth() / 2, getHeight() / 4 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH, 
					getWidth() / 2 - HIP_WIDTH, getHeight() / 4 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH );
			add(leftHip);			
			GLine leftLeg = new GLine(getWidth() / 2 - HIP_WIDTH, getHeight() / 4 + HEAD_RADIUS + ROPE_LENGTH + BODY_LENGTH, 
					getWidth() / 2 - HIP_WIDTH, getHeight() / 4 + HEAD_RADIUS + ROPE_LENGTH + BODY_LENGTH + LEG_WIDTH);
			add(leftLeg);			
		}
		else if (inCount == 2) {			
			GLine rightHip = new GLine(getWidth() / 2, getHeight() / 4 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH, 
					getWidth() / 2 + HIP_WIDTH, getHeight() / 4 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH);
			add(rightHip);			
			GLine rightLeg = new GLine(getWidth() / 2 + HIP_WIDTH, getHeight() / 4 + HEAD_RADIUS + ROPE_LENGTH + BODY_LENGTH, 
					getWidth() / 2 + HIP_WIDTH, getHeight() / 4 + HEAD_RADIUS + ROPE_LENGTH + BODY_LENGTH + LEG_WIDTH);
			add(rightLeg);
		}
		else if (inCount == 1) {			
			GLine leftFoot = new GLine(getWidth() / 2 - HIP_WIDTH, getHeight() / 4 + HEAD_RADIUS + ROPE_LENGTH + BODY_LENGTH + LEG_WIDTH, 
					getWidth() / 2  - (HIP_WIDTH + FOOT_LENGTH) , getHeight() / 4 + HEAD_RADIUS + ROPE_LENGTH + BODY_LENGTH + LEG_WIDTH);
			add(leftFoot);
		}
		else if (inCount == 0) {
			GLine rightFoot = new GLine(getWidth() / 2 + HIP_WIDTH, getHeight() / 4 + HEAD_RADIUS + ROPE_LENGTH + BODY_LENGTH + LEG_WIDTH, 
					getWidth() / 2  + (HIP_WIDTH + FOOT_LENGTH) , getHeight() / 4 + HEAD_RADIUS + ROPE_LENGTH + BODY_LENGTH + LEG_WIDTH);
			add(rightFoot);
		}
	}
	// create the hanging scafold helper, per spec...
	public void createScaffold () {
	    GLine beam = new GLine(getWidth()/ 2, getHeight() / 4, getWidth()/2 - BEAM_LENGTH, getHeight()/ 4 );
	    add(beam);		    			
	    GLine rope = new GLine(getWidth() /2 , getHeight() / 4, getWidth() /2, getHeight() / 4 + ROPE_LENGTH);		
	    add(rope);		    			
	    GLine scaffBar = new GLine(getWidth() / 2 - BEAM_LENGTH, getHeight() / 4, getWidth() / 2 - BEAM_LENGTH, getHeight() / 4 + SCAFFOLD_HEIGHT ); 
	    add(scaffBar);
	}
	
	
	
	// ** Constants for the simple version of the picture (in pixels) 
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_WIDTH = 108;
	private static final int FOOT_LENGTH = 28;
	GLabel tLabel1, tLabel2;
}

