public class TicTacToe{

    private String[][] board;
    private int row = 0;
    private int col = 0;
    private int counter = 0;

    public TicTacToe(int row, int col){
        
        if (ZeroBoundary(row, col)){
            throw new IllegalArgumentException("Row and Cols must be greater than 0");
        }
        
        this.row = row;
        this.col = col;

        board = new String[row][col];

    }

    private boolean ZeroBoundary(int row, int col){
        if (row <= 0 || col <= 0){
            return true;
        }

        return false;
    }

    public String playNext(int row, int col){


        if (row <= 0 || col <= 0){  
            throw new IllegalArgumentException("Row and Cols must be greater than 0"); 
        }

        if (row >= this.row || col >= this.col){
            throw new IllegalArgumentException("Row and Cols inputs must be less than the board col and row"); 
        }

        if (this.counter%2 == 0){
            board[row][col] = "X";
            return "X";
        }

        board [row][col] = "O";
        return "O";

    }

    public String toString(){



        return "not Implemented";

    }
    




}