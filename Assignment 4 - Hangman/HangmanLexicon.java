
/*
 * file HangmanLexicon.java 
 * ------------------------
 * This class creates a "lexicon" of words to choose for this
 * game.  Within the constructor, it reads in a text file stores away in an array
 * and is available for the program to extract a secret word
 * when needed.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {
	
    public HangmanLexicon () {    	
    	BufferedReader bReader = openFile("HangmanLexicon.txt");    	
    	bList = new ArrayList<String>();
    	createList(bReader);    	    	
    }    	
    
    // create the array list...
    private void createList (BufferedReader inBuff) {
    	try {
    		while (true) {
    			String bLine = inBuff.readLine();
    			if (bLine == null) {
    				break;
    			}    			
    			bList.add(bLine);  // adding by EACH line to the array list...
    		}
    		inBuff.close();
    	}
    	catch (IOException ex) {
    		throw new ErrorException(ex);
    	}    	
    }
    
	// Create/open the buffered reader..
    private BufferedReader openFile (String inFile) {
    	BufferedReader bTemp = null;    	
    	while (bTemp == null) {
    		try {
    			bTemp = new BufferedReader(new FileReader(inFile));
    		}
    	    catch (IOException ex) {
    		    System.out.println("Cannot open this file");
    	    }
    	}
    	return bTemp;
    }
    
	// returns the number of words in the lexicon
	public int getWordCount() {		
		return bList.size() -1;  // array count subtract 1 due to index starting at 0. Rgen start = 0.
	}
	
	// returns the word leveraging of off passed index
	// note, that work count does a "-1" due to array indexes starting at 0.
	public String getWord(int index) {		
		return bList.get(index);		
	}

	// want access to these regardless to method hiding....	
	public ArrayList<String> bList;
}
