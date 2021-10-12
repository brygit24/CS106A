from karel.stanfordkarel import *

"""
File: DoubleBeeper.py
-----------------------   
  * Karel doubles the number of beepers on the corner directly in front of him in the world.
  * After that, Karel returns to his initial starting place. 
  Approach:
  - move to the pile of beepers, this is assumed to be one in front
  - call the function doublebeepers in pile
    * doublebeepers in pile
       - call a function that doubles in the ave in front (east)
       - call a function that returns that pile
       - move to initial starting place
       
       ==> DONE (BP)
"""


def main():
    move()  # again, assuming that beeper is directly in front.
    doublebeepersinpile()  # sitting on pile = entrance
    movebackward()  # back to start


def doublebeepersinpile():
    """
    - Entrance criteria: Sitting on a pile of beepers(count unknown)
    - Exit: Pile is doubled in the same spot.
     Strat:
     - Double beepers in subsequent pile
       - while beepers
         - pick up beeper
         - move forward
         - put 2 beepers
         - move back
     movedouble = double beepers in forward spot.
     returnpile = move a pile backwards one ave.
     Note: This COULD be decomposed more.
    """
    movedouble()  # move forward and double
    move()  # to get to next pile
    returnpile()  # move beepers back on ave.


def returnpile():  # move a pile of beepers WEST by on ave.
    # entrance: sitting on pile of beepers
    # exit: sitting on the pile of beepers in INITIAL place
    while beepers_present():
        pick_beeper()
        uturn()
        move()
        put_beeper()
        uturn()
        move()
    movebackward()   # get back to starting pos of this function call


def movedouble():
    # move and double existing pile of beepers one place forward and return
    # entrance criteria: sitting on top of pile o beepers
    # exit criteria: facing NEW pile of DOUBLED beepers
    while beepers_present():
        pick_beeper()
        move()
        put_beeper()
        put_beeper()
        movebackward()


def movebackward():  # move back and face east.
    uturn()
    move()
    uturn()


def uturn():
    turn_left()
    turn_left()


# There is no need to edit code beyond this point

if __name__ == "__main__":
    run_karel_program()
