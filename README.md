# ChessGame
Created by Nick Eissler

## Description
### GamePieces: 
Folder containing class for each different piece.  Each class determines the valid moves each piece can make and if these moves are obstructed.

### Spot.java:
Class for a spot on the board, responsible for showing image of piece as well as handling input from the GUI when tiles are selected.

### Board.java:
Class that contained the gameboard including the pieces on it.  Handles validity of moves, including castling and promotion, as well as end of game conditions.

### ChessGui.java:
Responsible for GUI of board, including drawing buttons for pieces, promotion display, and end of game display.

### Game.java:
Driving class to run game

## How to Play:
Rules follow standard chess.  White moves first with white and black going in alternate turns, and no illegal or out of turn moves are allowed. Game will
terminate at first instance of checkmate or stalemate.

