import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class EasterCalculatorTest {

  @Test
  void testCase1(){
    assertEasterDate("March 22", EasterCalculator.easterDate(1818));
  }
  @Test
  void testCase2(){
    assertEasterDate("March 31", EasterCalculator.easterDate(1771));
  }
  @Test
  void testCase3(){
    assertEasterDate( "April 1", EasterCalculator.easterDate(1584));
  }

  @Test
  void testCase4(){
    assertEasterDate("April 25", EasterCalculator.easterDate(1943));   
  }

  @Test
  void testCase5(){
    assertEasterDate(null, EasterCalculator.easterDate(1583));
  }

  @Test
  void testCase6(){
    assertEasterDate("April 6", EasterCalculator.easterDate(4098));
  }
  @Test
  void testCase7(){
    assertEasterDate(null, EasterCalculator.easterDate(4099));
  }

  @Test
  void testCase8(){
    assertEasterDate("April 18", EasterCalculator.easterDate(1954));
  }

  @Test
  void testCase9(){
    assertEasterDate("April 19", EasterCalculator.easterDate(1981));
  }

  @Test
  void testCase10(){
    assertEasterDate("April 18", EasterCalculator.easterDate(2049));
  }

  @Test
  void testCase11(){
    assertEasterDate("April 19", EasterCalculator.easterDate(2076));
  }

  private void assertEasterDate(String expected, MyDate actual) {
    if (actual == null) {
      assertEquals(expected, actual);
    } else {
      assertEquals(expected, actual.toString());
    }
  }

}