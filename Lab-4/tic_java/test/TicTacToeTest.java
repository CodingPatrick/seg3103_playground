import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TicTacToeTest {

    @Test
    void TicTacToe_tc1(){
        // testing the default
        TicTacToe board = new TicTacToe(0,0);
        assertEquals(board.toString(), "No Board exists");
    }
    
}
