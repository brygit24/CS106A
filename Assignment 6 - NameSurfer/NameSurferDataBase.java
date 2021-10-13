/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import acm.program.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import acm.util.*;

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) 
 * Creates a new NameSurferDatabase and initializes it using 
 * the data in the specified file.  The constructor throws an
 * error exception if the requested file does not exist or if
 * an error occurs as the file is being read.
 * 
 */
	public NameSurferDataBase(String fileName) {		
		loadDataBase(fileName);						
	}
	// Loading the database and making accessible to this 
	// program via MAPPING into namesHashMap, which tracks
	// names ALREADY displayed in the graph.	
	private void loadDataBase(String inTextFile) {
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(inTextFile));
			while (true) {
				String bLine = bReader.readLine();
				if (bLine == null) {
					break;
				}
				else {
					// must go into the hashmap in the form of the "NameSurferEntry" data type
					NameSurferEntry bEntry = new NameSurferEntry(bLine);					
					String key = bEntry.getName();
					namesHashMap.put(key,bEntry);
				}   
			}
			bReader.close();
		}
		catch(IOException ex) {
			throw new ErrorException(ex);
		}	
	}	
	/* FIND ENTRY IN DATABASE OF NAMES DISPLAYED ON THE GRAPH!!
	*  Returns the nameSurferEntry associated with the name, if one
	*  exists. If the name does not appear in the database, this 
	*  method returns null. 
	*/
	public NameSurferEntry findEntry(String name) {		
		// perform a get on the names hash map
	    return namesHashMap.get(name.toLowerCase());		
	}
	// hashmap of NameSurferEntry data types.
	private HashMap<String, NameSurferEntry> namesHashMap = new HashMap<String, NameSurferEntry>();	
}

