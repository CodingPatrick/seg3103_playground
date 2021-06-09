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
    public void checkRow_tc7(){
        TicTacToe board = new TicTacToe(4,4);
        board.playNext(0, 0);
        board.playNext(0, 1);
        board.playNext(0, 2);
        board.playNext(0, 3);
        boolean n = board.checkRow(0, "X");
        assertEquals(n, true);
    }

/*
    @Test
    public void toString_tc6(){

    }

    @Test
    public void checkRow_tc7(){

    }

    @Test
    public void checkCol_tc8(){

    }

    @Test
    public void checkDiag_tc9(){

    }

    @Test
    public void winnerWinnerChickenDinner_tc10(){

    }
*/
}