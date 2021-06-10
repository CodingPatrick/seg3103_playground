public class TicTacToe {

    private String[][] board;
    private int row = -1;
    private int col = -1;
    private int counter = 0;

    public TicTacToe(int row, int col) {
        checkBoundary(row, col, true);
        this.row = row;
        this.col = col;
        board = new String[row][col];
    }

    private void checkBoundary(int row, int col, boolean initial) {
        if ((row <= 0 || col <= 0) && initial == true) {
            throw new IllegalArgumentException("Row and Cols must be greater than 0");
        }
        if (!initial && (row >= this.row || col >= this.col)) {
            throw new IllegalArgumentException("Row and Cols inputs must be less than the board col and row");
        }
        if ((row < 0 || col < 0) && initial == false) {
            throw new IllegalArgumentException("The input or specific field played must be greater than 0.");
        }
    }

    public String playNext(int row, int col) {
        checkBoundary(row, col, false);
        if (isNotEmpty(row, col)) {
            return "cannot play, field is occupied";
        }
        String turn = whoPlaysNext();
        counter++;
        board[row][col] = turn;
        return turn;
    }

    private boolean isNotEmpty(int row, int col) {
        return board[row][col] != null;
    }

    public String whoPlaysNext() {
        if (this.counter % 2 == 0) {
            return "X";
        }
        return "O";
    }

    public void reset() {
        this.board = new String[this.row][this.col];
        this.counter = 0;
    }

    public String getValue(int row, int col) {
        checkBoundary(row, col, false);
        return board[row][col];
    }

    public boolean checkRow(int row, String XO) {
        int boundary = this.col;
        int rowChecked = counter(row, XO, boundary);
        return rowChecked == this.col;
    }

    public boolean checkCol(int col, String XO) {
        int boundary = this.row;
        int colChecked = counter(col, XO, boundary);
        return colChecked == this.row;
    }

    // for a 3x3 board only
    public boolean checkDiag3x3() {
        Boolean diagChecked = diagonals(board[0][0], board[1][1], board[2][2], board[0][2], board[2][0]);
        return diagChecked;
    }

    public boolean diagonals(String square1, String square2, String square3, String square4, String square5) {
        if (square1 == square2 && square2 == square3) {
            return true;
        }
        if (square5 == square2 && square2 == square4) {
            return true;
        }
        return false;
    }

    public int counter(int line, String XO, int boundary) {
        counter = 0;
        for (int i = 0; i < boundary; i++) {
            if (board[line][i] == XO && boundary == this.col) {
                counter++;
            }
            if (board[i][line] == XO && boundary == this.row) {
                counter++;
            }
        }
        return counter;
    }

    // for a 3x3 board only
    public boolean hasWinner3x3(int row, int col, String XO){
        if (checkRow(row, XO) == true || checkCol(col, XO) == true || checkDiag3x3() == true){
            return true;
        }
        return false;
    }

    public boolean boardIsFull3x3(int row, int col, String XO){
        counter = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if((board[i][j] == "X" || board[i][j] == "O")){
                    counter++;
                }
            }
        }
        if (counter == 9 && ! hasWinner3x3(row, col, XO)){
            return true;
        }
        return false;
    }

    // this was used to produce a failed test
    public String toString() {
        return "not implemented";
    }
}