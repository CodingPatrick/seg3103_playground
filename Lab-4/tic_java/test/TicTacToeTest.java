import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class TicTacToeTest {

    @Test
    public void TicTacToe_tc1(){
        // testing the default 
        assertThrows(IllegalArgumentException.class, () -> new TicTacToe(0,0) );
    }
    
}
