from karel.stanfordkarel import *

"""
File: TripleKarel.py
--------------------
When you finish writing this file, TripleKarel should be
able to paint the exterior of three buildings in a given
world, as described in the Assignment 1 handout. You
should make sure that your program works for all of the 
Triple sample worlds supplied in the starter folder.

==> DONE (BP)
"""

# note, this works on all associated worlds
# per spec.  The sizes may change but the pattern is the same
# which is what is coded for.

# paint each building, common denominator and assumption
# is that when your at end of building must turn right
# to paint next. No conditionals between buildings yet.


def main():
    paint1building()
    turn_right()
    paint1building()
    turn_right()
    paint1building()

# paint 1 building
# use left wall then a turn then a move


def paint1building():
    left_wall()
    turn_left()
    move()
    left_wall()
    turn_left()
    move()
    left_wall()


# painting the wall to the of karel
# to deal with corners, using left is blocked.


def left_wall():
    while left_is_blocked():
        put_beeper()
        move()


def turn_right():
    turn_left()
    turn_left()
    turn_left()


# There is no need to edit code beyond this point

if __name__ == "__main__":
    run_karel_program()
