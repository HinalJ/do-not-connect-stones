# Do-not-Connect-Stones Game

###  Information:
- Game board consist of 5x5 cells.
- Every cell is empty in the beginning.
- Every cell can contain a piece of stone:
    - Red: Player1
    - Blue: Player2
    
### Game Play:
- When the game is launched the program will ask for the names of the players.
- Players move in turns.
- In a move, a player must choose an empty cell and put his or her stone into it.

### Game Ends When:
- A row, a column, or a diagonal contains 3 stones of the same color.
- The three stones do not have to be adjacent. 
- The player to which these stones belong loses the game.
- The game will also end if the board is entirely filled and it doesn't contain any 3 same colored adjacent stones.

### Game Database:
- The game will store the result of the game as follows:
    - The date and time when the game was started, 
    - The name of the players, 
    - The number of moves made by the players during the game and
    - The name of the winner. 
- If the game is tied, there will be option to Rematch or Exit, in such a case database will not be saved.
    
### Game High-Score Table:
- The table displays the top 5 players with:
    - The most wins and
    - The most recent win. 

## Requirements

Building the project requires JDK 11 or later and [Apache Maven](https://maven.apache.org/).
