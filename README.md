

# Paths - a game application project

## Introduction

Paths is a choice-based interactive storytelling application. The project was developed for the final assignment for 
the course "IDATA2001 Programming 2" at NTNU spring 2023.

![Screen dump](/target/classes/mainPage.png)
A screen dump of the final application's main page. 

## Release notes

| **Version** | **Description**                                                                                                             |
|-------------|-----------------------------------------------------------------------------------------------------------------------------|
| v0.1        | Created the project as a **Maven** project. Implemented the basic classes and the logic.                                    |
| v0.2        | Added file handling and functional programming. Made a sketch of the graphical user interface.                              |
| v0.3        | Applied design patterns, and implemented the graphical user interface from the sketch. Extended the program with own ideas. |

## Description of the project

The project is made to represent a choice-based interactive storytelling application with a graphical user interface implemented 
in JavaFX. The application reads and parse a file, which is written in the **.paths**-format.  
![Screen dump](/target/classes/examplePathsFile.png)
An example of a **.paths**-file.

The file must be written in the right format and must contain information for only one story to be able to play.
The story can not contain any broken links, links who do not refer to a passage, as well. When all criteria are
fulfilled the game begin. The player need to create a player and goals as well, to be able to start the game.
The player walk through the passages by the link displayed as buttons. Each link may contain one action or more. 
The actions are executed on the player and updates the scoreboard. The game is won if all goals are fulfilled, and lost
if the player's health, score or gold reaches 0. When the game is over, the user is able to save the game, restart 
or return to the main menu and start a new game.

### Technologies used in the project
* **Java** is used as the primary language for developing this project. It's responsible for
the foundation of the application and logic as well. 
* **IntelliJ** is used for code editing, debugging and project management. 
* **Maven** is used to manage the project's dependencies and build process. 
* **JavaFx** is used to create the interface for the application. This includes creating GUI components,
handling user input and managing visual components. 
* **CSS**, Cascading Style Sheets, is used to manipulate the appearance of the JavaFX user interface. 

#### JavaFX concepts demonstrated in the project
The project does **not** make use of JavaFXML or SceneBuilder, but builds the GUI from within the Java code. 
* General JavaFX structure: Stage, Scene, SceneGraph, Nodes etc.
* Event handling, using Lambda
* Buttons with graphics
* File handling: import and read a **.paths**-file, and keep persistent data in local files as well. 
The user import a file to start a game, and export the file when finished. 
* Alerts to display information to the user.

### Challenges and further improvement
Challenges
* **Module-info.java** was created to be able to run the program successfully. Since it was not a part
of the courses' curriculum, this was made with help of a teacher's assistant. 
* **

Further improvement
* **Read .png, .mov and .mp4 from file**. Reactor the story file handler to be able to read different 
types of file formats. So, when a player goes from one passage to another a picture, movie or sound will
play.
* **Create different verities of mini-games**. This version of the project only has one mini-game, the dice 
rolling mini-game. To further improvement more mini-games are added. When the player tries to enter a passage,
a random mini-game from the collection is selected. 

