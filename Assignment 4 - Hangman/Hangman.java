
import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;

/* file: Hangman.java 
 * Emulating the classic childhood game.
 */

  public class Hangman extends ConsoleProgram {	  
	  public static void main(String [] args) {
			new Hangman().start(args);
	  }	  
	  // add the hangman canvas
	  public void init() { 
	      setSize(700,700);
	      pause(800);
	      canvas = new HangmanCanvas();
	      add(canvas);		  
	  }
	  public void run () {
	      setFont("Times New Roman-24");	      
	      playGame();
	      while (roundCheck()) {
	          guessCount = 8; // RESETTING NEW GAME!!!
	    	  playGame();	    	  
	      }
	      println("DONE WITH HANGMAN. GOOD DAYE!...");	      
	      remove(canvas);  
	  }
	  //top controller
	  private void playGame() {
	      setup();		  
	      while (!turnOver()) {			  
	          runRound();
	      }  
	  }	
	  // Setting the canvas, getting a random secret word updating status to player
	  private void setup() {
	      bar2 = "";
	      canvas.reset();		 
	      int vx = rgen.nextInt(0, tLex.getWordCount());// remember, -1 in get word due indexing...		  
	      secretWord = tLex.getWord(vx);	// set the secretword 
	      println("Welcome to hangman!!");
	      secretWordStatus = createStatusBar(secretWord);		  
	      canvas.displayWord(secretWordStatus);
	  }
	  // create INITAL guessed status bar/tracker
	  private String createStatusBar (String inStr) {
	      String bar1 = "";
	      for (int i = 0; i < inStr.length(); i++) {
	          bar1 += "-";
	      }
	      return bar1;	  
          }
	  // player guesses character until correct guess or out of guesses
	  private boolean turnOver() {
	      if (testAnswer(secretWord, secretWordStatus)) {
	          println("You guessed the correct word! " + secretWord);
		  println("You win!");
		  return true;
	      }
	      if (guessCount <= 0) {
	          println("You are completely hung!!");
		  println("You lose!");
		  println("The secret word was: " + secretWord);
		  return true;
	      }
	      else {			  
	          return false;
	      }		  
	  }
	  // compare the status to the secret word, assist with game ending.
	  private boolean testAnswer (String inWord, String inStatus) {
	      int fCount = 0;
	      for (int i = 0; i < inStatus.length(); i++) {
	          if (inWord.charAt(i) != inStatus.charAt(i)) {
		      fCount ++;
		  }			  
              }
	      if (fCount > 0) {
	          return false;
	      }
	      else {
	          return true;
	      }
	  }	  
	  //display status, get character guess, update
	  private void runRound() {
	      println("");		      
	      println("Your Guess Status is:");
	      println(secretWordStatus);
	      println("You have " + guessCount + " guesses left");
	      guessedChar = getGuess();
	      // status of how many characters guessed correctly
	      secretWordStatus = checkForCharacter(secretWord, secretWordStatus, guessedChar);
	      // put it on the canvas
	      canvas.displayWord(secretWordStatus);	  
	  }
	  //check for character and update status bar...
	  private String checkForCharacter (String inWord, String status, char inChar) {		 
	      int checkIt = inWord.indexOf(inChar);		  
		  if (checkIt != -1) {  // checking if inChar in secret word.
		      println("Your Guess is correct!");	  
			  return upDateStatusLine(inWord, status, inChar);			 
		  }		  
		  else {
		      guessCount --;
			  println("There are no " + inChar + "'s" + " in the word" );
			  trackBadGuess(inChar);
			  return status;
		  }				  
	  }
	  // track bad characters, update in canvas.
	  private void trackBadGuess(char inChar) {		  
	      bar2 += inChar;
	      canvas.noteIncorrectGuess(bar2, guessCount);
	  }	  
	  // update the status line "----x---"	  
	  private String upDateStatusLine(String inWord, String inStatus, char inChar) {		  
	      int index = inWord.indexOf(inChar); 
	      while (index >= 0) { // this works because if index = null, -1 is returned!!    		  
    	          inStatus = inStatus.substring(0, index) + inChar + inStatus.substring(index + 1);
    	          index = inWord.indexOf(inChar, index + 1);
    	      }
              return inStatus;        	  
	  } 
	  // get a valid character guess.
	  private char getGuess() {
	      // do checking/validation similar to what I did in swap file here....
	      char guessOut;	      
	      String guess = readLine("Your guess..");
	      while (!isLegalChar(guess)) {
	          println("Invalid...");
	          guess = readLine("Re-enter Letter ");	    	  
	      }		  
	      guessOut = guess.charAt(0);
	      return Character.toUpperCase(guessOut);		  
	  }	  
	  // character validator
	  private boolean isLegalChar(String inStr) {		  
	      if (inStr.length() != 1 ) {
	          println("wrong length..");
		  return false;
	      }
	      char tChar = inStr.charAt(0);
	      if (Character.isDigit(tChar)) {
	          println("NO NUMERICS!");
		  return false;
	      }
	      return true;		  
	  }	  
	  // determine if a NEW game/session is started after a turn
	  private boolean roundCheck() {  // note, funky behavior if misconstrued keystroke....
	      String testLn = readLine("READY FOR ANOTHER ROUND OF HANGMAN? ");		  
	      if (testLn.charAt(0) == 'Y' || testLn.charAt(0) == 'y') {
	          return true;
	      }
              else {
	          return false;   
	      }		   		    
	  }	  
          // instance variables..
	  private String secretWord, bar2;
	  private char guessedChar;
	  private int guessCount = 8;
	  private String secretWordStatus, secretword;
	  private HangmanCanvas canvas;
	  private HangmanLexicon tLex = new HangmanLexicon();
	  private RandomGenerator rgen = RandomGenerator.getInstance();
}
  
