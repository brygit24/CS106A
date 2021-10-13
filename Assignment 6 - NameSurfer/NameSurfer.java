/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import acm.graphics.*;

public class NameSurfer extends Program implements NameSurferConstants {
	
	public static void main(String[] args) {
		new NameSurfer().start(args);		
	}	
	// create control strip, add action listeners, read in text file and create
	// database, add the name surfer graphic canvas
	public void init() {	    
		setSize(600,600);
		createController();
		addActionListeners();
		namesDataBase = new NameSurferDataBase("names-data.txt"); // create db, read in text file.				
		graph = new NameSurferGraph();
		add(graph);			
	}	
	//creates control strip..
	public void createController() {
		nameField = new JTextField(MAX_NAME);
		nameField.addActionListener(this);
		graphButton = new JButton("Graph");
		clearButton = new JButton("Clear");		
		add(new JLabel("Name"), NORTH);
		add(nameField,NORTH);  
		add(graphButton,NORTH);
		add(clearButton,NORTH);				
	}
	// detect button and call corresponding method	
	public void actionPerformed(ActionEvent e) {		
		Object source = e.getSource();
		if (source == nameField || source == graphButton) {  			
			if (nameField.getText().isEmpty() == true) {				
				println("TEXT is REQUIRED for LOOKUP!!");
			}
			else {
				String name = nameField.getText();				
				NameSurferEntry entry = namesDataBase.findEntry(name);				
				if (entry != null) {					
					graph.addEntry(entry); // add entry to the list of "GRAPHED" entries					
					graph.update(); // reconfigure/draw the graph
				}			
		    }
		}			
		else if (source == clearButton) {			
			graph.clear();
		}		
	}
	
	//constant
	private static final int MAX_NAME = 25;	
	//instance variables
	private JTextField nameField;
	private JButton graphButton;
	private JButton clearButton;	
	private NameSurferGraph graph;	
	private NameSurferDataBase namesDataBase;
}
