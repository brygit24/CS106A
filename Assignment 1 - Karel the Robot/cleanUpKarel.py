from karel.stanfordkarel import *

"""
File: cleanUpKarel.py
-----------------------
  * Karel starts at 1,1 facing east and cleans up any scattered beepers in the world.
  Approach
  * start by picking up the initial row, function "cleanRow" 
  * if, another row, move up, turn to west  (rep to west), or exit
  * cleanup row
  * if, another row, move up, turn east   (rep to east) or exit
  * clean row
  * Code for when no row and how to graceful exit.
  
  ==> DONE (BP)
"""


def main():
    cleanRow()    # we know there is at least one row
    while left_is_clear():  # making LEFT CLEAR as predicate / dependence on rest of prog. at the 1st east most wall.
        repoWest()    # position to clear WESTWARD row
        cleanRow()
        if right_is_clear():  # if there is a sub row, move up, to east
            repoEast()    # reposition
            cleanRow()
        else:
            uTurn()   # U-turn here, left is NOT clear meaning prog will come out of this!!


def repoEast():  # reposition
    # entrance: facing a wall WEST
    # exit: next north ave facing EAST
    turn_right()
    move()
    turn_right()


def repoWest():   # reposition
    # entrance: facing a wall, pointing EAST
    # exit: next north ave facing WEST
    turn_left()
    move()
    turn_left()


def uTurn():
    turn_left()
    turn_left()


def turn_right():
    turn_left()
    turn_left()
    turn_left()


def cleanRow():  # pick up any existing beepers from a row/ave
    # works east or west assume, that turn and determination has occurred.
    # entrance: facing east or west(horizontal) AT START of ROW
    # exit: same: facing east/west/horizontal  AT END OF ROW
    while front_is_clear():
        if beepers_present():
            pick_beeper()
        else:
            move()
    if beepers_present():   # this deals with the issue of there being a beeper on the edge wall!!
        pick_beeper()


# There is no need to edit code beyond this point

if __name__ == "__main__":
    run_karel_program()
