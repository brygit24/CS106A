from karel.stanfordkarel import *

"""
File:  democracy.py
-----------------------
A section exercise.  Essentially checking "punch stamps" on voting ballots.

Solving the "Karel Defends Democracy" section 1 problem 
 ** high level approach:
    * while front is clear is predicate
    * move to 1st rectangle
    * check if beeper is in the middle column
      - if true, 
         - check if "full"(funct?)
           - if full, no action
           - else, not full populate( funct, extra spec did not mandate) RETURN TO GATE!
      - if not/else  NO beep in middle
          - check for garbage( )
             - if garbage, clear out(function) RETURN TO GATE!!
             - if not/else  CLEAR /move out
    * move to next rectangle
    * graceful program exit.
	
	==> DONE(BP)
"""


def main():
    move()
    while front_is_clear():
        if beepers_present():
            moveNextRect()    # add the check for full later.... spec says if only 1 its ok...
        else:
            garbCheck()     # note naming not too good, could go back but will get better with more progs/time.
            moveNextRect()


def garbCheck():
    # check if rectangle has garbage,
    # if not, move on
    # is so clean it up, either north or south clean
    # entrance:  in the gate, middle of rectangle
    # exit: in the gate, with garbage cleaned. facing east
    # note, coded for the fixed rectangle size and variable # of beepers
    turn_left()
    move()
    if beepers_present():
        northBeeperClean()
    else:
        gotoSouthWall()
    if beepers_present():
        southBeeeperClean()
    else:
        return2Middle()


def return2Middle():  # get back to gate from south wall
    uTurn()
    move()
    turn_right()


def gotoSouthWall():
    uTurn()
    move()
    move()  # facing south


def southBeeeperClean():  # pick up beepers going south and return to opening in wall
    while beepers_present():  # in case there are multiple beepers.
        pick_beeper()
    uTurn()
    move()
    turn_right()


def northBeeperClean():  # pick up beepers going to the north and return to opening in wall
    while beepers_present():  # in case there are multiple beepers.
        pick_beeper()
    uTurn()
    move()
    move()

def moveNextRect():
    move()
    if front_is_blocked():  # in case there is a move then a wall.  May need mod later....
        fullTemp()
    else:
        move()


def fullTemp():
    turn_left()
    turn_left()
    turn_left()
    turn_left()


def uTurn():
    turn_left()
    turn_left()


def turn_right():
    turn_left()
    turn_left()
    turn_left()

# There is no need to edit code beyond this point

if __name__ == "__main__":
    run_karel_program()
