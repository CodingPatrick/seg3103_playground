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
        assertEquals(board.playNext(1,1), "O");
    }
    
}
