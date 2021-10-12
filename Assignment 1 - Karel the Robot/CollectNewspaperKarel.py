from karel.stanfordkarel import *

"""
File: CollectNewspaperKarel.py
------------------------------
At present, the CollectNewspaperKarel file does nothing.
Your job in the assignment is to add the necessary code to
instruct Karel to walk to the door of its house, pick up the
newspaper (represented by a beeper, of course), and then return
to its initial position in the upper left corner of the house.

==> DONE (BP)
"""


def main():
    goto_paper()
    get_paper()
    come_back()


def goto_paper():
    move()
    move()
    turn_right()
    move()
    turn_left()
    move()


def get_paper():
    pick_beeper()


def come_back():
    turn_around()
    move()
    turn_right()
    move()
    turn_left()
    move()
    move()
    put_beeper()
    turn_around()


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
