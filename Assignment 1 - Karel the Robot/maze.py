from karel.stanfordkarel import *

"""
File: maze.py
-----------------------
    Karel solves the age old problem of escaping a maze!!!
    Karel will move through the maze and get out.  Beeper indicates the exit.
    
    To solve this problem, I had to do some research, this is an age old problem
    which has an age old algorithm to solve which is called:
    
    THE RIGHT HAND RULE!!!!
    
    If you KEEP your right hand on the wall, you should eventually get out.
    
    Coded Karel leveraging off this algorithm.
    
    ==> DONE (BP) 
"""
"""   
   Approach:
   - find nearest, first vertical wall and position so right hand is on it
   - move through maze with right hand on wall
      - if right is blocked, go
      - if right is open, move appropriate
      - if front is blocked, move appropriate      
"""


def main():
    findFirstVerticalWall()   # predicate for rest of prog
    rightHandMove()   # keeping hand on right wall!


def rightHandMove():
    while right_is_blocked():  # predicate
        if no_beepers_present():
            if front_is_clear():
                move()
            if right_is_clear():  # meaning no wall to right to put hand
                getToRightWall()
            if front_is_blocked():
                turn_left()
            if beepers_present():
                moveToSafeEnd()   # cant figure a gracefull exit has something to do with being
                                  # incumbent on the right_wall.  Moving on for now.
                                  # this meets the condition to get out of while right blocked  HACK!!


def moveToSafeEnd():
    pick_beeper()
    turn_left()
    move()
    turn_right()
    move()
    turn_left()
    move()
    move()
    turn_left()
    move()

def getToRightWall():   # find the NEXT right wall to keep moving regardless of circumstance.
    turn_right()
    move()
    if right_is_clear():
        turn_right()
        move()

def findFirstVerticalWall():
    """
    Find the first vertical wall and position so karel's right hand is on
    pre-condition: at 1,1 facing east
    post-condition: at 1st vertical wall facing north
    """
    while front_is_clear():
        move()
    turn_left()

def turn_right():
    turn_left()
    turn_left()
    turn_left()

def fullTurn():
    turn_left()
    turn_left()
    turn_left()
    turn_left()

# There is no need to edit code beyond this point

if __name__ == "__main__":
    run_karel_program()
