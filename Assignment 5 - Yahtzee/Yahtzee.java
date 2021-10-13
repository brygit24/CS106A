/* File: Yahtzee.java
 * This emulates the game of Yahtzee, there are pre-compiled libraries
 * Some for early implementation and get replaced later and some that
 * are supplied to assist.
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import acm.io.*;
import acm.program.*;
import acm.util.*;


public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] inArgs) {
		new Yahtzee().start(inArgs);
	}	
	public void run() {		
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter the number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {  // indexing the players names, to start at 1 instead of 0
            playerNames[i - 1]	= dialog.readLine("Enter the name for player " + i);              
		}				
		display = new YahtzeeDisplay(getGCanvas(), playerNames);		
		playGame();		
	}
	// entry/start...
	public void playGame() {		 
		// "ghost", "clone", "copy" for tracking purposes..
		// note how indexing "+1" works.  Categories START at cat1 AND players START at player 1.		
		scoreCard = new int[N_CATEGORIES+1][nPlayers+1];		
		booleanScoreCard = new boolean[N_CATEGORIES][nPlayers+1];	 // for checking previous scores player basis		
		// cycle through the number of player and number of turns per player
		for (int turns = 0; turns < N_SCORING_CATEGORIES; turns++) {
		//for (int turns = 0; turns < 2; turns++) {	
			for (int player = 1; player <= nPlayers; player++) { // code DEALS with player indices starting at 1
				firstRoll(player);
				secondAndThirdRoll();
				selectCategory(player);				
			}			
		}	
		// Make the FINAL TOTAL SCORE CALCULATIONS.
	    updateFinal();	    
		declareWinner();	    
	}	
	//... 
	private void firstRoll(int inPlayer) {
		display.displayDice(dice); 		
		display.printMessage("It's " + playerNames[inPlayer-1] + "'s turn, click ROLL DICE button to roll dice "); 
		display.waitForPlayerToClickRoll(inPlayer);  		
		rollAllDie(dice);
		display.displayDice(dice);
	}
	//...
	private void secondAndThirdRoll() {
		display.printMessage("Select the Dice to Re-roll and do Again(roll2)");
		display.waitForPlayerToSelectDice();		
		for (int i = 0; i < dice.length; i++) {
			if (display.isDieSelected(i)) {				
				rollOneDie(i,dice );
			}
		}
		display.displayDice(dice);
		display.printMessage("Select the Dice to Re-roll and do Again(roll3)");
		display.waitForPlayerToSelectDice();
		for (int i = 0; i < dice.length; i++) {
			if (display.isDieSelected(i)) {				
				rollOneDie(i,dice );
			}
		}
		display.displayDice(dice);	
		display.printMessage("end of roll3, TIME TO ADD YOUR SCORE");				
	}
	// INITAL roll of ALL dice
	public int [] rollAllDie(int [] inArr) {
		for (int i = 0; i < inArr.length; i++) {
			inArr[i] = rgen.nextInt(1,6);			
		}		
		return inArr;
	}	
	// this is for the re-rolls
	// pass the dice array in...
	public void rollOneDie (int arrIndex, int [] inArr) {
		int tInt = rgen.nextInt(1,6);
		inArr[arrIndex] = tInt;
	}	
	// select category, check if previous value, do the math for the score.
	private void selectCategory(int inPlayer) {				
		int category = display.waitForPlayerToSelectCategory();
		// Score Categories that DONT require dice validation....
	    // It's safe to BYPASS checkCategory for valid dice roll, since the dice rolls happen elsewhere and are trusted..
		if ((category >= ONES && category <= SIXES) || (category == CHANCE)) {  // Any roll is valid, if no matching #'s,  user gets a "0"			
			// checking for previous value which, leverages off of "ghost" boolean array...
		    if (checkPreviousScore(category, inPlayer) == true) {			    	
				display.printMessage("YOU HAVE ALREADY SELECTED THIS CAT, PICK ANOTHER!!");
				selectCategory(inPlayer);
		    }
		    else if (checkPreviousScore(category, inPlayer) == false) {
		    	updateSumScores(inPlayer, category);
		    }			    
		}		
		// Score Categories that DO require valid rolls..
		if (category >= THREE_OF_A_KIND && category <= YAHTZEE) {			
			if (checkDiceCategory(dice, category) == true){
				if (checkPreviousScore(category, inPlayer) == true) {			    	
					display.printMessage("YOU HAVE ALREADY SELECTED THIS CAT, PICK ANOTHER!!");
					selectCategory(inPlayer);
			    }		
			    else if (checkPreviousScore(category, inPlayer) == false){			    	
			    	lowerScoreRouter(category, inPlayer);			        
			    }			    
			}			  	
			else if ((checkDiceCategory(dice, category) == false)) {
			    if (checkPreviousScore(category, inPlayer) == true) {			    	
					display.printMessage("YOU HAVE ALREADY SELECTED THIS CAT, PICK ANOTHER!!");
					selectCategory(inPlayer);
			    }
				else if (checkPreviousScore(category, inPlayer) == false) {	// the SELECTION failed, put zeros!!				
					putZeros(category, inPlayer);					
				}
			}
		}			
	}
	// method to check previous score value..
	// NOTE LEVERAGING OFF OF a GHOST BOOLEAN scorecard..	
	private boolean checkPreviousScore(int inCat, int inPlayer) {		
	    if (booleanScoreCard[inCat][inPlayer] == true) {		
		    return true;
		}
		else {			
		    return false;
		}		
	}	
	// "updateSumScores" is called from different methods when a users ROUND total is in need of calculation
	// updateSumScores calculates score for the Categories that are
	// "summed" up.  2 types... Plain old sum incumbent
	// on SAME dice type = "ONES" ==> "SIXES".  ONLY sum the MATCHING
	// dice.  If user picks INCORRECT cat, the sum will AUTOMATICALLY
	// calculate to 0. Also, the other cat for this method is "CHANCE", which simply sums
	// ALL of the dice.  Coded for these 2 in this method.
	private void updateSumScores(int inPlayer, int inCat) {
		int sum = 0;
		// sum ALL of the dice = CHANCE
		if (inCat == CHANCE) {
		    for (int j = 0; j < dice.length; j++) {
				sum = sum + dice[j];
			}
		}
		else if (inCat != CHANCE) {
			// ONLY sum dice in ACCORDANCE with INCAT...(1-6)
			for (int i = 0; i < dice.length; i++) {	    	
		    	if (dice[i] == inCat) {  // getting the value EVERYTIME the players' column is crossed	    		
		    		sum = sum + dice[i];	    		
		    	}
		    }		
		}	        
		updateSums(inCat,inPlayer,sum);	    
		updatePlayerTotal(inPlayer);	    
	}	
	// for upperScores.
	private void updateSums(int inCat, int inPlayer, int sum) {
		display.updateScorecard(inCat,  inPlayer,  sum);  // magic score keeper
		scoreCard[inCat][inPlayer] = sum;  // my internal score tracker
		booleanScoreCard[inCat][inPlayer] = true;  // my tracker to test previous scores...		
	}
	// updating the players total, this happens per round/3rolls...
	private void updatePlayerTotal(int inPlayer) {
		int total = 0;
		for (int i = 1; i < N_CATEGORIES-1; i++ ) {
			for (int j = 1; j < scoreCard[0].length; j++) {
				if (j == inPlayer && (i < 16)) {
					total = total + scoreCard[i][j];
				}
			}
		}
		display.updateScorecard(TOTAL, inPlayer, total);  // note that "TOTAL" is a constant in the def file....
		scoreCard[TOTAL-1][inPlayer] = total;  // note deal with "TOTAL-1"		
	}
	// method to route/check the lowerScores
	private void lowerScoreRouter (int inCat, int inPlayer) {
		// 3 or 4 of a kind = sum ALL dice
		// NOTE: rule is must be AT LEAST 3 or 4 of kind
		// if 4 of kind selected and user picks 3 of kind, its ok...
	   	if (inCat == THREE_OF_A_KIND || inCat == FOUR_OF_A_KIND) { // calculation for 3 of and 4 of kind is the same    		
	   	    sumThreeOrFourOfKind(inCat, inPlayer);    		
	   	}    	
	   	// full house, 3 of one value and 2 of another  points = 25
	   	else if (inCat == FULL_HOUSE) {    		
	   		updateSums(inCat, inPlayer, 25);
	   		updatePlayerTotal(inPlayer);
	   	}
	   	// small straight, 4 consecutive values let "magic" verify , then add 30 points
	   	else if (inCat == SMALL_STRAIGHT) {    		
	   		updateSums(inCat, inPlayer, 30);
	   		updatePlayerTotal(inPlayer);
	   	}
	   	// large straight 5 consecutive values... let "magic" verify, then add 40
	   	else if (inCat == LARGE_STRAIGHT) {    		
	   		updateSums(inCat, inPlayer, 40);
	   		updatePlayerTotal(inPlayer);
	   	}
	   	// Yahtzee!! = 5 of a kind. Let "magic" verify, then add 50
	   	else if (inCat == YAHTZEE) {    		
	   		updateSums(inCat, inPlayer, 50);
	   		updatePlayerTotal(inPlayer);
	   	}    	
	}	
	// a method to add ZEROS into display, the score card and the boolean tracker..
	private void putZeros(int inCat, int inPlayer) {
		display.updateScorecard(inCat,  inPlayer, 0);  // update the SE scorecard with their method.
		scoreCard[inCat][inPlayer] = 0;            // my score tracker
		booleanScoreCard[inCat][inPlayer] = true;  // my boolean tracker		
	}	
	// called from LOwer Score router...
	// to sum ALL of dice values	
	private void sumThreeOrFourOfKind (int inCat, int inPlayer) {		
		int sum = 0;
		for (int i = 0; i < dice.length; i++) {
			sum = sum + dice[i];
		}
		updateSums(inCat, inPlayer, sum);
		updatePlayerTotal(inPlayer);
	}	
	// REPLACING "MAGIC STUBB" for FULL CREDIT!!
	// In early implementation, relied on the yahtzeelib's checkcategory
	// As programming progressed, I had to create my own "validator"
	// This method VALIDATES dice rolls.  Returns true if correct, false if not.
	private boolean checkDiceCategory (int [] inArr, int inCat) {
	    diceMap.clear();  // to ensure there's no foo data!!.
	    arrayToMap(inArr, diceMap);  // to assist with calculations...?? should I "parametorize" ?
		// any dice roll valid, I don't call with these cats, but if someone did, it would work.
		if ((inCat >= ONES && inCat <= SIXES) || (inCat == CHANCE)) {   // any roll is valid for these..		    	  
		    return true;  // in addition, this is redundant, NOT called in opening. But if got here, would work!
		}	      
		else if (inCat == THREE_OF_A_KIND || inCat == FOUR_OF_A_KIND) {  // note; 3 of a kind - if at LEAST 3 of kind.  If player has < 3 its ok...		      			
		    if (checkthreeOrFourOfKind(dice)) {
			    return true; // there are not INVALID rolls, trust there are 5 dice....
			}
		}	      
		else if (inCat == FULL_HOUSE) {		      
		    if (diceMap.containsValue(3) && diceMap.containsValue(2)) {// we know if there is 3 and 2 of kind = full house!
			    return true;
			}			
		}
		else if (inCat == SMALL_STRAIGHT) {		      
		    if (checkSmallStraight(inArr, smallStraightMap, smallStraightList)) {
		        return true;
		    }
		}
		else if (inCat == LARGE_STRAIGHT) {		      
		    if (checkLargeStraight()) {
			    return true;
			}
		}
		else if (inCat == YAHTZEE) {
		    if (diceMap.containsValue(5)) {  
			    return true;
		    }
		}	      
		return false;// the DEFAULT will be to return FALSE, each cat will return TRUE IF, or else land here and return false.		
	}	
	// set the map with the # of occurences... VIA the Incoming ARRAY!! 
    // the sort/creation of the ARRAY is the catalyst to subsequent calculations!!!
    // called from "checkDiceCategory" and "checkSmallStraight
    private void arrayToMap(int [] inArr, HashMap<Integer, Integer> inMap) {
	    for(int i: inArr) {  // let's read into the map directly from the dice array AND put up counts!!!
		    if(inMap.containsKey(i)) {  // check if previous value for index/number...		    	  
			    inMap.put(i,  inMap.get(i) + 1);  // i = key, get delivers the key value.
		    }
		     else {
			     inMap.put(i, 1); // if no PREVIOUS key, add and make the value 1.
		     }
	    }		
    }	
	// called from "checkDiceCategory"
    // checkDiceCategory will call this if player selects 3 OR 4 of a kind.    
    private boolean checkthreeOrFourOfKind(int [] inArr) {
  	    for (Integer index : diceMap.keySet()) {  //looping map, key = index....from this...command
		    Integer numVal = diceMap.get(index);  // getting the value of the key "index"		      
		        if (numVal >= 3) {  // if there is an key that has 3 or more entries, were good for 3 or four of kind.  No other test requirement					
	                return true;					
		        }								
	    }
  	    return false;
    }    
    // map the dice for this method.. 
    // convert the data back into an arrayLIST
    // return true if test passes or false otherwise.
    private boolean checkSmallStraight(int [] inArr, HashMap<Integer, Integer> inMap, ArrayList<Integer> inArrList) {
  	    // let's read into the map directly from the dice array AND put up counts!!!
  	    // REQUIRED to get rid of DUPLICATEs to perform the validity calculation.
        // call array to map with "smallStraightMap"..
        arrayToMap(inArr, inMap);  	  
  	    // if a set of dice has more than 2 duplicates, it can't be a small straight.
  	    for(Integer index : inMap.keySet()) {
		    Integer numVal = inMap.get(index);		      
		    if (numVal > 2) {
		        return false; 
		    }
		    else {
		        inArrList.add(index); // converting map to an ARRAYLIST for processing...further calculations.
		    }
	    }  	        	  
  	    // find smallest number, find largest number the do math....
  	    int min = findSmallestInList(inArrList);
  	    int max = findLargestInList(inArrList);
  	    if (max - min == inArrList.size() - 1) { // the proof!!        		  
    	    return true;
    	}              
  	    return false;        	  
    }
    // finds the smallest integer in an ARRAYLIST...
    private int findSmallestInList(ArrayList<Integer> inArrList) {
	      int sml = inArrList.get(0);
		  for(int i : inArrList) {  // note: using the for Each construct!!!
		      if (sml > i) {
			      sml = i;
			  }			
		  }
		  return sml;
	}
    // find the largest integer in an ARRAYLIST...
    private int findLargestInList(ArrayList<Integer> inArrList) {
		int lg = inArrList.get(0);
		for (int i : inArrList) {
			if (lg < i) {
				lg = i;
			}
		}
		return lg;
	}    
    //...
    private boolean checkLargeStraight() {
  	    int min = findSmallestInArray(dice);
	    int max = findLargestInArray(dice);	    	      
	    //the math!! the formula!      
	    if (max - min == dice.length - 1) { // the proof!!		    	    		
		    return true;
	    }
	      return false;
    }
    // prior to getting the algorithm for straight and large straight,  
    // must find the smallest first...
    private int findSmallestInArray(int [] inArr) {
	    int sml = inArr[0];
	    for(int i : inArr) {  // note: using the for Each construct!!!
	        if (sml > i) {
		        sml = i;
	        }			
	    }		  
	    return sml;
    }
    // prior to getting the algorithm for straight and large straight,
    // must find the largest first.
    private int findLargestInArray(int [] inArr) {
	    int lg = inArr[0];
	    for (int i : inArr) {
		    if (lg < i) {
			    lg = i;
		    }
	    }		
	    return lg;
    }    
    // total the final scores.
 	private void updateFinal() {	    
 	    for (int i = 1; i <= nPlayers; i++) {
 	        updateUpperScore(i);
 	    	updateBonusScore(i);
 	    	updateLowerScore(i); 
 	    	updateTotalScore(i);
 	    }	    
 	}	
 	//...
 	private void updateTotalScore(int inPlayer) {
 		int totalScore = 0;
 		totalScore = scoreCard[UPPER_SCORE][inPlayer] + scoreCard[UPPER_BONUS][inPlayer] + scoreCard[LOWER_SCORE][inPlayer];
 		scoreCard[TOTAL][inPlayer] = totalScore;  // my tracker...
 		display.updateScorecard(TOTAL, inPlayer, totalScore);  // official display via yahtzeelib
 	}	
 	//...
 	private void updateLowerScore(int inPlayer) {
 		int lowerScore = 0;
 		for (int i = THREE_OF_A_KIND; i < LOWER_SCORE; i++) {
 			for (int j = inPlayer; j <= nPlayers; j++) {
 				if (j == inPlayer) {  // ONLY SUM FOR INPLAYER!!! {
 					lowerScore = lowerScore + scoreCard[i][j];	
 				}				
 			}
 		}
 		scoreCard[LOWER_SCORE][inPlayer] = lowerScore;
 		display.updateScorecard(LOWER_SCORE, inPlayer, lowerScore); // official display via yahtzeelib
 	}
 	// Calculate the upper bonus, if applicable
 	private void updateBonusScore(int inPlayer) {
 		if (scoreCard[UPPER_SCORE][inPlayer] >= 63) {
 			scoreCard[UPPER_BONUS][inPlayer] = 35;
 			display.updateScorecard(UPPER_BONUS, inPlayer, 35);
 		}
 	}
 	//...
 	private void updateUpperScore(int inPlayer) {
 	    int upperScore = 0;
 		for (int i = 1; i < UPPER_SCORE; i++) {
 		    for (int j = inPlayer; j <= nPlayers; j++) {
 			    if (j == inPlayer) {  // ONLY SUM FOR INPLAYER!!!
 				    upperScore = upperScore + scoreCard[i][j];	
 				}				
 			}
 		}
 		// update 2 scorecards, mine and the official via yahtzeelib
 		scoreCard[UPPER_SCORE][inPlayer] = upperScore;
 		display.updateScorecard(UPPER_SCORE, inPlayer, upperScore);
 	}	
	//...
	private void declareWinner() {
		int bigFinal = scoreCard[TOTAL][1];
		String bigFinalPlayerName = playerNames[0];
		for (int i = TOTAL; i <= TOTAL; i++) {
			for (int j = 1; j <= nPlayers; j++) {
				if (scoreCard[TOTAL][j] > bigFinal) {
					bigFinal = scoreCard[TOTAL][j];
					bigFinalPlayerName = playerNames[j-1];
				}
			}
		}
		display.printMessage("Game over! Winner is: " + bigFinalPlayerName + "  With the Score: " + bigFinal);
	}	
	// private instance variables..
	private HashMap <Integer, Integer> diceMap = new HashMap<Integer, Integer>();
	private HashMap <Integer, Integer> smallStraightMap = new HashMap<Integer, Integer>();
	private ArrayList<Integer> smallStraightList = new ArrayList<Integer>();	
	private boolean [][] booleanScoreCard; // using "copy" or "mirror" or "ghost" or whatever its called for tracking...
	private int [] dice = new int [N_DICE];  // the constants says "4", which fails, changed to "5", plus using the variable
	//int [] dice = {1,2,3,4,5}; // hard code for testing...
	private int [] [] scoreCard;  // for internal .vs display scorecard tracking... unless another way!??
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();	
}
