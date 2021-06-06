public class TicTacToe{

    private String[][] board;
    private int row = -1;
    private int col = -1;
    private int counter = 0;

    public TicTacToe(int row, int col){
        
        checkBoundary(row, col, true);
        
        this.row = row;
        this.col = col;

        board = new String[row][col];

    }

    private void checkBoundary(int row, int col, boolean initial){
        if (row <= 0 || col <= 0){
            throw new IllegalArgumentException("Row and Cols must be greater than 0"); 
        }

        if ( !initial && (row >= this.row || col >= this.col) ){
            throw new IllegalArgumentException("Row and Cols inputs must be less than the board col and row"); 
        }

        
    }

    public String playNext(int row, int col){

        checkBoundary(row, col, false);


        if (isNotEmpty(row, col)){
            return "cannot play, field is occupied";
        }

        if (this.counter%2 == 0){
            counter++;
            board[row][col] = "X";
            return "X";
        }

        counter++;
        board [row][col] = "O";
        return "O";

    }


    private boolean isNotEmpty(int row, int col){
        return board[row][col] != null;
    }   

    public String whoPlaysNext(){
        if (this.counter%2 == 0){
            return "X";
        }
        return "O";
    }


    public String toString(){
        return "not Implemented";
    }
    




}