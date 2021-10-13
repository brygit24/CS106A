/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
	
	// constructor also adds the component listener
	public NameSurferGraph() {
	    addComponentListener(this);		
	}	
	// clears the list of name surfer entries stored inside this class
	public void clear() {	
	    entries.clear();  // clear the database/arraylist of GRAPHED entries		
	    removeAll();  // wipe the graph clean				
	    buildGraphOutLine(); // rebuild the graph
	}	
	//  CALLED FROM NAMESURFER to request that this entry gets into the "GRAPH DISPLAYED" list.
	// "entries" is an arraylist created and managed from THIS class.
	public void addEntry(NameSurferEntry inEntry) {		
	    entries.add(inEntry);		
	}
	/* method update()
	 * Updates the display image by deleting all graphical objects
	 * from the canvas and then reassembling the display according
	 * to the list of ENTRIES.  "update" is called after either the
	 * "clear" button or "addentry" method.  Update is also 
	 * called whenever the size of the canvas changes.	
	 */
	public void update() {		
	    removeAll();
	    buildGraphOutLine();		
	    if (entries.size() > 0) {
	        buildEntries();
	    }	
	}	
	// buildEntries iterates through list of names in DB and calls 
	// the graph method.  Deals with colors as well
	private void buildEntries() {	
	    int colorDex = 0;
	    for(NameSurferEntry entry: entries) {			
	        String name = entry.getName();			
		String rankStr = entry.getRank();				        
		int [] rankArr = createRankArr(rankStr); // calling helper to convert ranks string into ranks array	
		// call to draw one graph			
		drawOneGraph(rankArr, name, colorDex);
		colorDex++;
	    }	
	}
	//graph drawing method		
	private void drawOneGraph(int [] rankArr, String inName, int inDex) {
	    //int iDex = 2;
	    for (int i = 0; i < NDECADES -1; i++) {			
	        // set the Y coordinates for this part of line...
		double workSpace = getHeight() - (SPACER * 2); // space between top and bottom spacers.
		// the amount of SLOTS in the workspace used to determine the rank.
		// which is workspace / 1000(the amount of ranks we are tracking)
		double workSpaceVerticalSlot = workSpace/MAX_RANK;		
		// amount/size of horizontal slots					
		double workSpaceHorizontalSlot = getWidth() /(double) NDECADES;
		/* Set the color for this entry */			
		Color color = getColor(inDex);			
		double firstY;
		double secondY;
		if (rankArr[i] == 0) {
	            firstY = SPACER + workSpace;			
		}
		else {//(rankArr[i] != 0) {
		    firstY = (topColHeight+ (workSpaceVerticalSlot * rankArr[i]));
		}
		// the 2nd one..
		if (rankArr[i+1] == 0 ) {
		    secondY = SPACER + workSpace;
		}
		else { //if (rankArr[i+1] != 0)
		    secondY = (topColHeight+ (workSpaceVerticalSlot * rankArr[i+1]));				
		}
		// set the X coord for this part...
		double firstX = i * workSpaceHorizontalSlot;				
		double secondX = (i + 1) * workSpaceHorizontalSlot;			
		GLine bLine = new GLine(firstX, firstY, secondX, secondY);			
		bLine.setColor(color);
		add(bLine);			
		// set the label text
		String entryLabel;
		if (rankArr[i] != 0) {				
		    entryLabel = inName + " " + Integer.toString(rankArr[i]);
		}
		else {
		    entryLabel = inName + " *";
		}
		// set the entry's label coords
		double labelX = firstX;
		double labelY = firstY;
		// add the entry's label
		GLabel bLabel = new GLabel(entryLabel, labelX, labelY);
		// color....
		bLabel.setColor(color);
		add(bLabel);			
		// FENCEPOST PROBLEM!!!  THE WAY the loop is set
		// there is a fencepost problem here with regard to LABELS
		// a solution is to loop again when at the end and ONLY 
		// add the label cause the line is done FENCEPOST....
		if (i + 1 == NDECADES - 1) { // if index = 10... 
		    labelX = secondX;					
		    labelY = secondY;				
		    if (rankArr[rankArr.length -1] != 0) {
		        entryLabel = inName + " " + Integer.toString(rankArr[rankArr.length - 1]);						
		    }
		    else {
		        entryLabel = inName + " *";
		    }
		}
		GLabel bLabel2 = new GLabel(entryLabel, labelX, labelY);			
		bLabel2.setColor(color);
		add(bLabel2);			
	    }      
	}			
	// take the rank string and return a rankArray	
	private int [] createRankArr(String inStr) {
	    String [] stringArr = inStr.split(" ");
	    int [] outArr = new int[11];
	    for (int i = 0; i < stringArr.length; i++) {
                outArr[i] = Integer.parseInt(stringArr[i]);
	    }
	    return outArr;
	}	
	// buildgraphoutline does the work of creating/re-creating the graph
	private void buildGraphOutLine() {			
	    int labelText = 1900; // there is probably a better way to deal with the year labels...
	    double startLabel = 0;			
	    double nextX = 0;		
	    double columnSlots = getWidth() / (double) NDECADES;
	    for (int i = 0; i < NDECADES; i++) {							
	        GLine line = new GLine(nextX, getHeight() - getHeight(), nextX, getHeight());				
		GLabel label = new GLabel(String.valueOf(labelText));			
		label.setColor(Color.BLUE);			
		add(line);
		add(label, startLabel , getHeight());				
		nextX += columnSlots;
		startLabel += columnSlots;
		labelText = labelText + 10;
	    }
	    // borders....
	    GLine bottomBorder = new GLine(getWidth() - getWidth() , getHeight() - SPACER,  getWidth(), getHeight() - SPACER);			
	    GLine topBorder = new GLine(0, 0 + SPACER, getWidth(), 0 + SPACER);			
	    add(bottomBorder);
	    add(topBorder); 			
	}		
	// index in the ArrayList (as passed through as parameter) */
	private Color getColor(int indexOf) {
	    if (indexOf % 5 == 1) {
	        return Color.red;
	    } 
	    else if(indexOf % 5 == 2) {
	        return Color.blue;
	    } 
	    else if (indexOf % 5 == 3) {
	        return Color.magenta;
	    }
	    else if (indexOf % 5 == 4) {
	        return Color.green;
	    } else 
	        return Color.black;
        }	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	// private instance variables...	
	private static final int SPACER = 20;
	private static final int NDECADES = 11;	
	private static final int MAX_RANK = 1000;	
	private double topColHeight = getHeight() - getHeight() + SPACER; // top + spacer	
	private ArrayList<NameSurferEntry> entries = new ArrayList<NameSurferEntry>();	
}
