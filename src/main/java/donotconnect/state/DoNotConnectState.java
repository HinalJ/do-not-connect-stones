package donotconnect.state;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Slf4j
public class DoNotConnectState {
    private char[][] board = new char[5][5];

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


    public boolean isValidMove(int row, int col)
    {
        return board[row][col]!='o' && board[row][col]!='x';
    }

    public char draw(int row, int col, char turn)
    {
        log.info("Added {} at ({},{})", turn=='o'? "Red Stone": "Blue Stone", row, col);
        board[row][col] = turn;
        return turn;
    }

    public boolean isAllFilled(){
        for(int i = 0; i<5; i++)
            for(int j = 0; j<5; j++)
                if (board[i][j] != 'x' && board[i][j] != 'o')
                    return false;

        return true;
    }

}
