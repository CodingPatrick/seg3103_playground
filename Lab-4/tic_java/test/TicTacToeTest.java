import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class TicTacToeTest {

    @Test
    public void TicTacToe_tc1(){
        // testing the default 
        assertThrows(IllegalArgumentException.class, () -> new TicTacToe(0,0) );
    }

    @Test
    public void playNext_tc2(){
        // testing the default 
        TicTacToe board = new TicTacToe(3,3);
        assertEquals(board.playNext(1,1), "X");
    }

    @Test
    public void playNext_tc3(){
        // testing that I cannot overwrite a spot 
        TicTacToe board = new TicTacToe(3,3);
        assertEquals(board.playNext(1,1), "X");
        assertEquals(board.playNext(1,1), "cannot play, field is occupied");
    }
    
    @Test
    public void whoPlaysNext_tc4(){
        // testing who plays next
        TicTacToe board = new TicTacToe(3,3);
        board.playNext(1,1);
        assertEquals(board.whoPlaysNext(), "O");
    }

    @Test
    public void reset_tc5(){
        // testing who plays next
        TicTacToe board = new TicTacToe(3,3);
        board.playNext(1,1);
        board.reset();
        assertEquals(board.getValue(1,1), null);
    }

    @Test
    public void checkRow_tc6(){
        TicTacToe board = new TicTacToe(4,4);
        board.playNext(0, 0);
        board.playNext(0, 1);
        board.playNext(0, 2);
        board.playNext(0, 3);
        boolean n = board.checkRow(0, "X");
        assertEquals(n, false);
    }

    @Test
    public void checkCol_tc7(){
        TicTacToe board = new TicTacToe(4,4);
        board.playNext(0, 0);
        board.playNext(1, 0);
        board.playNext(2, 0);
        board.playNext(3, 0);
        boolean n = board.checkRow(0, "X");
        assertEquals(n, false);
    }

    @Test
    public void checkDiag3x3_tc8(){
        TicTacToe board = new TicTacToe(3,3);
        board.playNext(2, 2);
        board.playNext(2, 1);
        board.playNext(0, 0);
        board.playNext(0, 2);
        board.playNext(1, 1);
        boolean n = board.checkDiag3x3();
        assertEquals(n, true);
    }
 
    @Test
    public void hasWinner_tc9(){
        TicTacToe board = new TicTacToe(3,3);
        board.playNext(1, 0);
        board.playNext(2, 0);
        board.playNext(0, 0);
        board.playNext(0, 2);
        board.playNext(1, 1);
        board.playNext(2, 1);
        board.playNext(2, 2);
        boolean n = board.hasWinner3x3(0, 0, "X");
        assertEquals(n, false);
    }
}