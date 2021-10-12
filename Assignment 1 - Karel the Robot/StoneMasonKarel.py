from karel.stanfordkarel import *

"""
File: StoneMasonKarel.py
------------------------
When you finish writing code in this file, StoneMasonKarel should 
solve the "repair the quad" problem from Assignment 1. You
should make sure that your program works for all of the 
sample worlds supplied in the starter folder.

==> DONE (BP)
"""


def main():
    fix_column()   # per req this is start...
    while front_is_clear():     # per req the end is after a support column followed by wall.
        move4()                 # per spec each column is 4 apart.
        fix_column()


def move4():
    move()
    move()
    move()
    move()
    if no_beepers_present():  # if no beeper at the bottom of the column!
        put_beeper()


def fix_column():
    turn_left()
    while front_is_clear():
        if no_beepers_present():
            put_beeper()
        else:
            move()
    if no_beepers_present(): #no beeper at the top of column!
        put_beeper()
    turn_around()
    while front_is_clear():
        move()
    turn_left()     # want to leave karel facing east


def turn_right():
    turn_left()
    turn_left()
    turn_left()


def turn_around():
    turn_left()
    turn_left()


# There is no need to edit code beyond this point

if __name__ == "__main__":
    run_karel_program()
