/*   file:  breakOut.java  = Assignment #3   Create the game breakout!
 *   Emulating the game initially created by Steve Wozniak       
 */


import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import acm.util.RandomGenerator;


public class Breakout extends GraphicsProgram {
	
	public static void main(String [] args) {
		new Breakout().start(args);
	}	
	public void run () {	
		setSize(400, 600);
		pause(800);
		setUp();		
		vx = rgen.nextDouble(1.0, 2.5);		//ball speed range		
	    if (rgen.nextBoolean(0.5)) vx = -vx;  //ensuring ball starts going south	    
		while (!gameOver()) {
			moveCheck();						
		} 	
		exitStats();				
	}
	// setting up the bricks, paddle and adding the ball
	private void setUp() {		
		// setting up the coordinates and looping thru to create
		// bricks at the top with different colors in accordance with spec.		
		int xCoord = (WIDTH - ((NBRICKS_PER_ROW * BRICK_WIDTH) + ((NBRICKS_PER_ROW - 1)  * BRICK_SEP) ) )  / 2 ; // = 2		
		int yCoord = BRICK_Y_OFFSET + BRICK_HEIGHT;	 // = 78
		int COL_PASS = 0;
		for (int j = 0; j < NBRICK_ROWS; j++ ) {			
			putRow(xCoord, yCoord, NBRICKS_PER_ROW, COL_PASS);	
			yCoord = yCoord + BRICK_SEP	 + BRICK_HEIGHT;
			COL_PASS += 1;  // increment....
		}
		createPaddle();
		pause(1000);
		addBall();
	}
    // puts one row at a time...
	private void putRow(int xCoord, int yCoord, int inRow, int bCol) {
		for (int i = 0; i < inRow; i++) {			
			GRect tRect = new GRect(xCoord, yCoord, BRICK_WIDTH, BRICK_HEIGHT);
			add(tRect);			
			tRect.setFilled(true);
			// the set of if's below is how I'm dealing with the colors.
			// pretty archeac, but going with it until I learn a fancier command....
			if (bCol == 0 || bCol == 1) {
				tRect.setColor(Color.RED);	
			}			
			if (bCol == 2 || bCol == 3) {
				tRect.setColor(Color.ORANGE);
			}
			if (bCol == 4 || bCol == 5) {
				tRect.setColor(Color.YELLOW);
			}
			if (bCol == 6 || bCol == 7) {
				tRect.setColor(Color.GREEN);
			}
			if (bCol == 8 || bCol == 9) {
				tRect.setColor(Color.CYAN);
			}			
			xCoord = xCoord + BRICK_WIDTH + BRICK_SEP;		// increment...
		}
	}	
	// create the paddle and turn listeners on.
	private void createPaddle() {
	    bPaddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		bPaddle.setFilled(true);
		add(bPaddle, (getWidth() / 2) - (PADDLE_WIDTH /2) , getHeight() - PADDLE_Y_OFFSET);		
		addMouseListeners();
	}
	// mouse must get pressed FIRST prior to dragging.
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());  // initial mouse press/record...		
		gobj = getElementAt(last);  // this SELECTS the object, in this case the paddle.		
	}	
	// mouse dragging, after it is clicked and recorded mouse click/listen...
	public void mouseDragged(MouseEvent e) {  // this happens after click method...
		if (gobj != null) {
			gobj.move(e.getX() - last.getX(), 0);//y axis=same,e.get()=new/next mouse move for PADDLE(gobj)
			last = new GPoint(e.getPoint()); // tracking last mouse point/click			 
			   if(gobj.getX() < 0) { //LEFT screen; leverage obj's x. if Less then left side of screen..				
			       gobj.setLocation(0, getHeight() - PADDLE_Y_OFFSET); //putting paddle on edge everytime..
				   last = new GPoint(e.getPoint());// again, track mouse..
			   }
			   if (gobj.getX() > getWidth() - PADDLE_WIDTH) {// if paddles x causes to far right...
			       gobj.setLocation(getWidth() - PADDLE_WIDTH, getHeight() - PADDLE_Y_OFFSET); // back to edge..
			   }
		}			
	}	
	//...
	private void addBall() {
		gameBall = new GOval(BALL_RADIUS, BALL_RADIUS);
		gameBall.setFilled(true);
		add(gameBall, getWidth() / 2 - BALL_RADIUS / 2, getHeight() / 2 - BALL_RADIUS / 2);		
	}
	// setting ball speed and direction, checking for east, west and top walls. if encountered change direction of ball
	private void moveCheck() {		       	
		if (gameBall == null) {			
			addBall();
			pause(800);
		}
	    if (DIRECTION == SOUTH) {
			vy = rgen.nextDouble(1.0, 3.0);
			//East wall			
			if (gameBall.getX() > getWidth()) {
				vx = -vx;
			}
			// west wall
			else if (gameBall.getX() < 0) {				
				vx = rgen.nextDouble(1.0, 3.0);				
			}			
	    }	
	    else { //if (DIRECTION == NORTH) 
			vy = -rgen.nextDouble(1.0, 3.0);
			//East wall			
			if (gameBall.getX() > getWidth()) {
				vx = -vx;
			}
			// west wall
			else if (gameBall.getX() < 0) {				
				vx = rgen.nextDouble(1.0, 3.0);				
			}
			//top wall
			else if (gameBall.getY() < 0 + BALL_RADIUS * 2 && DIRECTION == NORTH) {
				vy = +vy;				
				DIRECTION = SOUTH;
		    }			
		}
	    // the actual move
		gameBall.move(vx,  vy);		
		pause(4);   				
		checkForCollisions();		
	}
	// checking for collision with the paddle
	private void checkForCollisions() {
		checkForPaddle();
		checkForBottom();
		checkForBricks();
	}
	//..
	private void checkForPaddle() {
		objBall = getElementAt(gameBall.getX(), gameBall.getY());  
		if (objBall != null) {  
			collider = getCollidingObject();
			if (collider == bPaddle) {  
				vy = -rgen.nextDouble(1.0, 3.0);
			    DIRECTION = NORTH;			    
			}
		}
	}
	// ..
	private void checkForBottom() {		
		if (gameBall.getY() > getHeight() - BALL_RADIUS * 2) {			
			remove(gameBall);
			gameBall = null;			
			vy = -rgen.nextDouble(1.0,3.0);						
			MISSED_BALLS += 1;
			//DIRECTION = NORTH;
		}		
	}	
	// checking for collisions with the bricks
	// and changing direction of ball is so
	private void checkForBricks() {		
		collider = getCollidingObject();		
		if (collider != null && collider != bPaddle) {
			remove (collider);
			if (DIRECTION == SOUTH) {				
				vy = -rgen.nextDouble(2.0, 5.0);
			    DIRECTION = NORTH;
			    BRICKHITS += 1;
			}
			else if (DIRECTION == NORTH) {
				DIRECTION = SOUTH;				
				vy = rgen.nextDouble(2.0, 5.0);			   
			    BRICKHITS += 1;
			}		
		}		   
	}	 
	// called from checkforbricks note technique for checking all 4 corners of object.
	private GObject getCollidingObject() {		
		if (gameBall == null) {  // AI: double check why this..
			return null;
		}		
		x2 = gameBall.getX() + BALL_RADIUS * 2;  // corner 2 clockwise  X coord 
		x3 = gameBall.getX() + BALL_RADIUS * 2; // corner 3 clockwise X coord
		y3 = gameBall.getY() + BALL_RADIUS * 2; // corner 3 clockwise Y coord
		y4 = gameBall.getY() + BALL_RADIUS * 2; // corner 4 clockwise Y coord
		
		if (getElementAt(gameBall.getX(), gameBall.getY()) != null) {  // standard corner
			return(getElementAt(gameBall.getX(), gameBall.getY()));  // if a hit return object	
		}
		if (getElementAt(x2 , gameBall.getY()) != null) {  // corner 2 clockwise
			return(getElementAt(gameBall.getX(), gameBall.getY())); // if hit return obj
		}
		if (getElementAt(x3, y3) != null) {  // corner 3, if a hit return...
			return(getElementAt(gameBall.getX(), gameBall.getY()));
		}
		if (getElementAt(gameBall.getX(), y4) != null) {
			return(getElementAt(gameBall.getX(), gameBall.getY()));			
		}
		else {
			return null;
		}		   	
	}	
	// ..
	private boolean gameOver() {		
	    return (MISSED_BALLS >= MAXBALLS || BRICKHITS >= MAXBRICKS );		
	}
	// ..
	private void exitStats() {
		println("END OF GAME!!");
		println();
		println("You MISSED THE BALL.." + MISSED_BALLS + " TIMES..");
		println();
		println("You Hit " + BRICKHITS + " BRICKS!");		
	}	
	// instance variables	
	// width and height of application window in pixels
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;
	
	// dimensions of the game board(usually the same...)
	public static final int WIDTH = APPLICATION_WIDTH;
	public static final int HEIGHT = APPLICATION_HEIGHT;
		
	// number of bricks per row..
	private static final int NBRICKS_PER_ROW = 10;
	
	// number of brick rows...
	private static final int NBRICK_ROWS = 10; // should be 10
	
	// separation between bricks...
	private static final int BRICK_SEP = 4;
		
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
		
	// height of each brick
	private static final int BRICK_HEIGHT = 8;
	
	// off set of where the bricks start at the top of pile and top of app/screen...
	private static final int BRICK_Y_OFFSET = 70;
	
	// paddle dimensions
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;
	
	// Offset of the paddle from the bottom of app/screen
	private static final int PADDLE_Y_OFFSET = 30;
	
	// radius of ball in pixels
	private final static int BALL_RADIUS = 10;
	
	// Set the direction off the bat	
	private String DIRECTION = SOUTH;
	
	// random generator instance
	private RandomGenerator rgen = RandomGenerator.getInstance();
		
	private int MAXBRICKS = 100;
	private int MAXBALLS = 10;
	
	// brick hit tracking
	private int BRICKHITS = 0;
	// instance() variables
	private int MISSED_BALLS = 0;
	
	private double vx, vy, x2,x3,  y2, y3, y4;
	private GPoint last;
	private GObject gobj, objBall, objBrick, collider;
	private GOval gameBall;
	private GRect bPaddle;		
}

