from karel.stanfordkarel import *

"""
File: beeperCollecting.py
A later exercise in the karel reader.  About decomposition
recognizing patterns, top-down design.

Given a world of towers of beepers this prog collects all the beepers
in a series of vertical towers and deposits them at southEast most corner.
At the end, Karel returns to his starting place and leaves a count of beepers 
he picked up on the southest corner of the world.

==> DONE (BP)
"""


def main():
    """
    This program is broken 3 main functions:
    - collectAllBeepers
    - dropAllBeepers
    - returnhome
    """
    collectAllBeepers()
    dropAllBeepers()
    returnHome()


def collectAllBeepers():   # get em all
    """
    Collects the beepers from EACH tower by moving along 1st row, calling "collectOneTower"
    at every corner.
    pre-condition: at southWest corner position
    post-condition: at the southEast corner position
    """
    while front_is_clear():   # predicate
        if beepers_present():
            collectOneTower()
            move()
        else:
            move()
    if beepers_present():  # in case there is a tower at the end...
        collectOneTower()


def collectOneTower():
    """
    Collects the beepers in a single tower.
    pre-condition: 1st street facing east
    post-condition: SAME...
    """
    turn_left()  # given precondition, we are going vertical to collect
    collectLineOfBeepers()   # specifically for beeper gathering
    uTurn()
    move2Wall()
    turn_left()


def collectLineOfBeepers():
    """
    collects a CONSECUTIVE line of beepers.   The end of the beeper line is indicated
    upon coming to a corner with NO beepers.
    * pre-condition: a line of beepers, but will work if NOT....
    """
    while beepers_present():
        pick_beeper()
        if front_is_clear():  # interesting tactic!! got this from reader! this will help meet above condition
            move()
    """
    Below is what I initially did, works but a mistake
    I used FRONT IS CLEAR .vs WHILE BEEPERS PRESENT
    But, the code below WOULD/COULD work if beepers went to the ENTIRE wall
    And/or if there were spaces in the beepers.  NOTE THE SPECS!!
    ******
    while front_is_clear():
        if beepers_present():
            pick_beeper()
            move()
        else:
            move()
    if beepers_present():  # in case the beepers go to the top...
        pick_beeper()
    """

def dropAllBeepers():
    """
    dropping the beeper bag contents at CURRENT CORNER
    for now, assume at the corner, or simply dump em all
    precondition: beeper bag has content
    post: empty bag
    """
    while beepers_in_bag():
        put_beeper()


def returnHome():
    """
    Returns karel to its initial position, 1,1  facing east
    Pre-condition: Karel facing east, which is what happens at end of "collectALL.."
    Post-condition: at position 1,1 east
    """
    uTurn()
    move2Wall()
    uTurn()

def move2Wall():
    # moves karel forward until blocked by a wall.
    while front_is_clear():
        move()


def uTurn():
    turn_left()
    turn_left()


def right_turn():
    turn_left()
    turn_left()
    turn_left()







# There is no need to edit code beyond this point

if __name__ == "__main__":
    run_karel_program()
