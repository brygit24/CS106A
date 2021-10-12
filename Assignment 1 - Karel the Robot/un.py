from karel.stanfordkarel import *

"""
File: un.py
-----------------------
The United Nations program builds houses on corners marked with rubble. 
Refer to req for building spec.  

Karel helps rebuild nations!!

==> Done (BP)
"""

def main():
    """

    """

    while front_is_clear():   # start/stop predicate, rubble is on the bottom.
        move()
        if beepers_present():   # upon finding debris, build new house
            buildHouse()

def buildHouse():
    """
    Pre-condition: bottom row, facing east sitting on a beeper
    Post: same, but NO beeper.
    """
    moveBack()  # we landed on beeper, to get to start of house structure.
    turn_left()    # pre-req for add3
    addThreeCols()    # first wall/column
    move()   # move to 2nd col
    turn_left()   # pre-req
    move()        # since there is a beeper here
    addThreeCols()  # 2nd col
    pick_beeper()    # pick up debris field
    move()
    turn_left()
    addThreeCols()   # final column


def addThreeCols():
    for i in range(3):
        put_beeper()
        move()
    uTurn()
    move2Wall()

def move2Wall():
    while front_is_clear():
        move()
    turn_left()     # reposition for subsequent pre-req

def moveBack():
    uTurn()
    move()
    uTurn()

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
