![](https://cf.geekdo-images.com/Sgg2B7kxtx8fFXz_2mPefA__opengraph/img/u7oY_IuMdJmX2X_xOlSd1XBsFNo=/0x179:3085x1798/fit-in/1200x630/filters:strip_icc()/pic7193695.png)

# My Shelfie Videogame - PSP01 Group
My Shelfie Board Game is the final test of "Software Engineering", course of "Computer Science Engineering" held at Politecnico di Milano (2022/2023).


## Overview
This project is a digital implementation in `Java` of <b>My Shelfie<b> board game made by <b>Cranio Games</b>.

In this game you will have to sort various objects that are on the board by placing them in your library. 
You will have to try to complete the formation you have been assigned but at the same time you will have to complete the two goals! 

## Project Specifications
The program is developed using the Model-View-Controller design pattern. Several UML diagrams are provided to clarify the structure and dynamics of the code.

A game instance is made up of a single server and multiple clients, which connect to it.

The implemented functionalities are:

- Basic and Complete Rules.
- Socket and RMI Connection.
- CLI: command line game interface. 
- GUI: graphical game interface realized using JavaFX.
- Persistence: games are saved automatically and can be resumed.
- Disconnection resiliance: players can disconnect and reconnect to the server.


## Running the Game
The game can me run with the jar file named <code>FILE.jar</code>, in the <code>/target</code> directory after building the project with Maven.

This file holds both the Server, the CLI and the GUI applications, one of which can be selected when booting.

To run the jar file, use the command <code>java -jar FILE.jar</code> from the command line in the jar's folder.

### Server

The machine running the server must be reachable from the clients in order to play the game. To start the server, select the <code>0</code> option when booting.  

By default, when launching this command, the server starts to listen for incoming messages.

Once launched, the server will print the events' log on the standard output.

### Client

The CLI and GUI versions of the client can be run by selecting the <code>0</code> and <code>1</code> options when booting respectively.

