package donotconnect.state;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing the state of the game.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Slf4j
public class DoNotConnectState {

    /**
     * Creates the board of 5x5 cells.
     */
    private char[][] board = new char[5][5];

    /**
     * Checks whether the game is finished or not from the last move
     * on respective row and column whether having same colored stones
     * in {@code Horizontal}, {@code Vertical}, {@code Diagonal} and
     * {@code Anti-Diagonal}.
     * @param row Row value where the last stone added.
     * @param col Col value where the last stone added.
     * @return {@code true} if the game is finished {@code false} otherwise.
     */
    public boolean isFinished(int row, int col)
    {
        //Horizontal
        if(row-2>=0)
            if((board[row-2][col]==board[row-1][col]) && (board[row-1][col]==board[row][col]))
                return true;
        if(row+2<=4)
            if((board[row][col]==board[row+1][col]) && (board[row+1][col]==board[row+2][col]))
                return true;
        if((row-1>=0) && (row+1<=4))
            if((board[row-1][col]==board[row][col]) && board[row][col]==board[row+1][col])
                return true;

        //Vertical
        if(col-2>=0)
            if((board[row][col-2]==board[row][col-1]) && (board[row][col-1]==board[row][col]))
                return true;
        if(col+2<=4)
            if((board[row][col]==board[row][col+1]) && (board[row][col+1]==board[row][col+2]))
                return true;
        if((col-1>=0) && (col+1<=4))
            if((board[row][col-1]==board[row][col]) && board[row][col]==board[row][col+1])
                return true;

        //Diagonal
        if(row-2>=0 && col-2>=0)
            if(board[row-2][col-2]==board[row-1][col-1] && board[row-1][col-1]==board[row][col])
                return true;
        if(row+2<=4 && col+2<=4)
            if(board[row+2][col+2]==board[row+1][col+1] && board[row+1][col+1]==board[row][col])
                return true;
        if((row-1>=0 && row+1<=4) && (col-1>=0 && col+1<=4))
            if(board[row-1][col-1]==board[row][col] && board[row][col]==board[row+1][col+1])
                return true;

        //Anti-Diagonal
        if(row-2>=0 && col+2<=4)
            if(board[row-2][col+2]==board[row-1][col+1] && board[row-1][col+1]==board[row][col])
                return true;
        if(row+2<=4 && col-2>=0)
            if(board[row+2][col-2]==board[row+1][col-1] && board[row+1][col-1]==board[row][col])
                return true;
        if((row+1<=4 && col-1>=0) && (col+1<=4 && row-1>=0))
            if(board[row+1][col-1]==board[row][col] && board[row][col]==board[row-1][col+1])
                return true;

        return false;
    }

    /**
     * Checks whether the cell is empty or not.
     * @param row Row of the cell clicked.
     * @param col Column of the cell clicked.
     * @return {@code true} if move is valid, {@code false} otherwise.
     */
    public boolean isValidMove(int row, int col)
    {
        return board[row][col]!='o' && board[row][col]!='x';
    }

    /**
     * Adds the respective colored stone to that cell.
     * @param row Row of the cell clicked.
     * @param col Column of the cell clicked.
     * @param turn Indicated whose turn was it red as 'o' or blue as 'x'.
     * @return whose turn was it red as 'o' or blue as 'x'.
     */
    public char draw(int row, int col, char turn)
    {
        log.info("Added {} at ({},{})", turn=='o'? "Red Stone": "Blue Stone", row, col);
        board[row][col] = turn;
        return turn;
    }

    /**
     * Checks whether the board is entirely filled with stones or not.
     * @return {@code true} if board is filled, {@code false} otherwise.
     */
    public boolean isAllFilled(){
        for(int i = 0; i<5; i++)
            for(int j = 0; j<5; j++)
                if (board[i][j] != 'x' && board[i][j] != 'o')
                    return false;

        return true;
    }

}
