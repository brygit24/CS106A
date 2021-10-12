from karel.stanfordkarel import *

"""
File: SteepleChase.py
-----------------------
Karel runs a SteepleChase!!

==> DONE (BP)
"""

def main():
    """
    Steeple Chase
    Spec:
    - starts a position 1,1 facing east
    - guarantee 9 aves long
    - Arbitrary hurdles at arbitrary heights, located between any 2 aves
    - karel should "jump" each one at a time

    Strat:
    - loop 9 times
    - if clear, move forward
    - if not clear, "jump hurdle"
    """
    for i in range(8):    # assuming 9 avenues
        if front_is_clear():
            move()
        else:
            jump_hurdle()


def jump_hurdle():
      # pre-condition: facing east at the bottom of hurdle
    """
      * post-condition: face east at the bottom of the next hurdle
      """
    ascend_hurdle()
    move()
    descend_hurdle()


def ascend_hurdle():
    """
    * pre-condition: facing east on the bottom of hurdle
    * post-condition: facing east at the top of the hurdle
    * looking to either descend, or check if at the top of another hurdle()
    """
    turn_left()     # north
    while right_is_blocked():   # hurdle height
        move()
    turn_right()     # exit, top of hurdle, facing east


def descend_hurdle():
    """
    * pre-condition: at the top of a hurdle facing east,
      looking to either turn right, return to bottom, or, CHECK for another hurdle
    * post condition: at the bottom of the hurdle facing east.
    * there is a move in the main jump hurdle.  If there are more that one consectutive hurdle, need more code
    """

    turn_right()   # assuming in position to go.
    while front_is_clear():
        move()
    turn_left()


def turn_right():
    turn_left()
    turn_left()
    turn_left()


# There is no need to edit code beyond this point

if __name__ == "__main__":
    run_karel_program()
