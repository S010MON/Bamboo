![workflow](https://github.com/S010MON/Bamboo/actions/workflows/linux.yml/badge.svg)
![workflow](https://github.com/S010MON/Bamboo/actions/workflows/windows.yml/badge.svg)
![workflow](https://github.com/S010MON/Bamboo/actions/workflows/mac.yml/badge.svg)

# Bamboo
An implementation of the game originally desgined by Mark Steere in March 21

### Gameplay
Bamboo is a two player game played on an initially empty hexagonal grid.  The 
two players, Red and Blue, place their own stones onto unoccupied cells on the 
board, one stone per turn.  Players are not allowed to pass.

### Placements 
A group is comprised of one or more interconnected, like colored, stones.  
A player’s group can’t contain more stones than the number of groups he has.

### Objective
The last player to place a stone wins.


A copy of the rules, with figures can be found here: http://www.marksteeregames.com/Bamboo_rules.pdf

<br/>

## Installation Instructions
Clone the source code repository and navigate to /Bamboo then run:

    $ gradle build
    
    $ gradle run

<br/>

## Dependencies
Ensure the following are installed before running:
- Java 16 or later
- Gradle 7.1

