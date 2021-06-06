public class TicTacToe{

    private boolean[][] board;
    private int row = 0;
    private int col = 0;

    public TicTacToe(int row, int col){
        
        if (ZeroBoundary()){
            throw new IllegalArgumentException("Row and Cols must be greater than 0");
        }
        
        this.row = row;
        this.col = col;

        board = new boolean[row][col];

    }

    private boolean ZeroBoundary(){
        if (row <= 0 || col <= 0){
            return true;
        }

        return false;
    }

    public String toString(){

        if (row == 0 && col == 0){
            return "No Board exists";
        }

        return "not Implemented";

    }
    




}