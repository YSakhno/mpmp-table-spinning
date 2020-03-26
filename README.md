# Solver for Matt Parker's Maths Puzzle (MPMP): Can you spin the table?

This is a small one source file solver for the puzzle presented by Matt Parker
in the YouTube video https://youtu.be/T29dydI97zY

## Running

You have to have at least Java (JDK) 8 installed (preferably to have JDK 11 or later version). You can download
one / find installation instructions here: https://adoptopenjdk.net/

Once JDK is properly installed, run the following command:

```
    $ ./gradlew run
```

(on Windows run the following command instead)

```
    gradlew.bat run
```

## What it does

By default (as is), it solves for the puzzle/case presented in the video (7 investors, _exactly_ one initially matches)
and outputs all investor seating arrangements that cannot be matched to the intended table arrangement by spinning the
table. If you wish, you can edit the main application file (`App.kt`) and change the constants at the top of the file.
