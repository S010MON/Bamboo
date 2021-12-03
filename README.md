![workflow](https://github.com/S010MON/Bamboo/actions/workflows/linux.yml/badge.svg)
![workflow](https://github.com/S010MON/Bamboo/actions/workflows/windows.yml/badge.svg)
![workflow](https://github.com/S010MON/Bamboo/actions/workflows/mac.yml/badge.svg)

# Bamboo
An implementation of the game originally desgined by Mark Steere in March 21

## Contents
- ![Gameplay](https://github.com/S010MON/Bamboo#gameplay)
- ![Dependencies](https://github.com/S010MON/Bamboo#dependencies)
- ![Installation](https://github.com/S010MON/Bamboo#installation-instructions)
- ![Preview](https://github.com/S010MON/Bamboo#preview)

<br/>

## Gameplay
Bamboo is a two player game played on an initially empty hexagonal grid.  The two players, Red and Blue, place their own stones onto unoccupied cells on the board, one stone per turn.  Players are not allowed to pass.

### Placements 
A group is comprised of one or more interconnected, like colored, stones.  
A player’s group can’t contain more stones than the number of groups he has.

### Objective
The last player to place a stone wins.

A copy of the rules, with figures can be found here: http://www.marksteeregames.com/Bamboo_rules.pdf

<br/>

## Dependencies
Ensure the following are installed before running:
- Java 17
- Gradle 7.1

<br/>

## Installation Instructions

### Executable .jar
An executeable jar file of the latest release is available ![here](https://github.com/S010MON/Bamboo/releases) or can be found by downloading the repository as a .zip and navigating to `/app/build/libs`.  Run the jar using the command `java -jar Bamboo-main/app/build/libs/app.jar` from the extracted repository. (In case of issues, check the java version used is 17 and that the app.jar file path is correct)

### Source
To compile the game from source clone the repository and navigate to `/Bamboo` then run:

    $ gradle build
    
    $ gradle run


## Preview
![Screenshot.png](https://github.com/S010MON/Bamboo/blob/main/app/src/main/java/Bamboo/view/resources/Screenshot.png)
