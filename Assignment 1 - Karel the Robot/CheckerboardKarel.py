from karel.stanfordkarel import *

"""
File: CheckerboardKarel.py
----------------------------
When you finish writing it, CheckerboardKarel should draw
a checkerboard using beepers, as described in Assignment 1. 
You should make sure that your program works for all of the 
sample worlds supplied in the starter folder.

==> DONE (BP)
"""

# assumptions
# different size worlds...


def main():
    paint__first_column()    # seeding 1st col regardless.
    while front_is_clear():  # exit the program criteria: when the south east wall blocked
        if beepers_present():
            move()
            turn_left()
            move()
            if front_is_blocked():      # 2 x 2   DEALING WITH ONLY 2 columns here...
                put_beeper()
                turn_around()
                move_to_wall()
            paint_col()    # NOTE: Dealing with 2 columns. if NOT 2 columns, continue with painting(ElSE)
        else:
            move()
            turn_left()
            paint_col()
    full_turn()    # arbitrary action at end of program
    full_turn()
    full_turn()


def paint_col():   # entrance criteria, facing north(up), ready to paint acts incumbent on beeper at bottom or not
    while front_is_clear():  # open space going north
        put_beeper()
        move()
        if front_is_blocked():  # EVEN VERTICAL walls
            evenvert()   # function deals with an even northern vertical wall, no paint, return
        else:
            move()
            if front_is_blocked():  # ODD VERTICAL walls
                oddvert()  # function paints, deals with an ODD northern vertical wall
                # note, reason for no ELSE statement, just pass/move on
    turn_left()   # assure that the end state of this function is facing EAST


def paint__first_column():  # hard coding the 1st column to seed rest of program.
    turn_left()    # criteria for rest of function!!
    if front_is_blocked():    ## ONE ROW!  and ONE COLUMN!!
        put_beeper()          ## need a beepper regardless
        turn_around()
        turn_left()     # criteria for sub-sequent function in one column...
        while front_is_clear():  # one row, x columns...
            move()
            if front_is_clear():
                move()
                put_beeper()
    while front_is_clear():
        put_beeper()
        move()
        if front_is_blocked():  # EVEN VERTICAL walls
            evenvert()   # function deals with EVEN vertical wall, no paint, return
        else:
            move()
            if front_is_blocked():  # ODD VERTICAL walls
                oddvert()  # function paints, deals with ODD vertical wall
            # note reason for no else here.  ONLY paint move in uneven wall scenario
    turn_left()   # assure that the end state of this function is facing EAST


def full_turn():
    turn_left()
    turn_left()
    turn_left()
    turn_left()


def move_to_wall():
    while front_is_clear():
        move()


def turn_around():
    turn_left()
    turn_left()


def evenvert():  # note no painting, beeper was previously dropped, need to return to south most
    turn_around()
    move_to_wall()


def oddvert():   # note, due to the un even wall, MUST PAINT, then southern return
    turn_around()
    put_beeper()  # this is painting the top of an uneven wall
    move_to_wall()


def turn_right():
    turn_left()
    turn_left()
    turn_left()


# There is no need to edit code beyond this point

if __name__ == "__main__":
    run_karel_program()
