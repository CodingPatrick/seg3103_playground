public class TicTacToe{

    private boolean[][] board;
    private int row = 0;
    private int col = 0;

    public TicTacToe(int row, int col){
        
        if (row <= 0 || col <= 0){
            throw new IllegalArgumentException("Row and Cols must be greater than 0");
        }
        
        this.row = row;
        this.col = col;

        board = new boolean[row][col];

    }

    public String toString(){

        if (row == 0 && col == 0){
            return "No Board exists";
        }

        return "not Implemented";

    }
    




}