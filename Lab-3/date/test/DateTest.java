import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DateTest {

  @Test
  void nextDate_tc01() {
    Date today = new Date(1700, 6, 20);
    Date expectedTomorrow = new Date(1700, 6, 21);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc02() {
    Date today = new Date(2005, 4, 15);
    Date expectedTomorrow = new Date(2005, 4, 16);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc03() {
    Date today = new Date(1901, 7, 20);
    Date expectedTomorrow = new Date(1901, 7, 21);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc04() {
    Date today = new Date(3456, 3, 27);
    Date expectedTomorrow = new Date(3456, 3, 28);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc05() {
    Date today = new Date(1500, 2, 17);
    Date expectedTomorrow = new Date(1500, 2, 18);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc06() {
    Date today = new Date(1700, 6, 29);
    Date expectedTomorrow = new Date(1700, 6, 30);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc07() {
    Date today = new Date(1800, 11, 29);
    Date expectedTomorrow = new Date(1800, 11, 30);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc08() {
    Date today = new Date(3453, 1, 29);
    Date expectedTomorrow = new Date(3453, 1, 30);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc09() {
    Date today = new Date(444, 2, 29);
    Date expectedTomorrow = new Date(444, 3, 1);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc10() {
    Date today = new Date(2005, 4, 30);
    Date expectedTomorrow = new Date(2005, 5, 1);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc11() {
    Date today = new Date(3453, 1, 30);
    Date expectedTomorrow = new Date(3453, 1, 31);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc12() {
    Date today = new Date(3456, 3, 30);
    Date expectedTomorrow = new Date(3456, 3, 31);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc13() {
    Date today = new Date(1901, 7, 31);
    Date expectedTomorrow = new Date(1901, 8, 1);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc14() {
    Date today = new Date(3453, 1, 31);
    Date expectedTomorrow = new Date(3453, 2, 1);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_tc15() {
    Date today = new Date(3456, 12, 31);
    Date expectedTomorrow = new Date(3457, 1, 1);
    assertEquals(expectedTomorrow, today.nextDate());
  }

  @Test
  void nextDate_invalid_tc16() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Date(1500, 2, 31)
    );
  }

  @Test
  void nextDate_invalid_tc17() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Date(1500, 2, 29)
    );
  }

  @Test
  void nextDate_invalid_tc18() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Date(-1, 10, 20)
    );
  }

  @Test
  void nextDate_invalid_tc19() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Date(1458, 15, 12)
    );
  }

  @Test
  void nextDate_invalid_tc20() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Date(1975, 6, -50)
    );
  }


  // The following 4 tests are for the 100% statement coverage (tc21 - tc24)
  @Test
  void nextDate_invalid_tc21() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Date(2021, 4, 31)
    );
  }

  @Test
  void nextDate_invalid_tc22() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Date(2021, 9, 32)
    );
  }

  @Test
  void nextDate_invalid_tc23() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Date(2020, 2, 30)
    );
  }

  @Test
  void toString_tc24() {
    Date today = new Date(3456, 11, 29);
    assertEquals(("3456/November/29"), today.toString());
  }


  // Trying to achieve 100% branch Coverage

  // Coverage for the if statement @118 in date.java
  @Test
  void toString_tc25() {
    Date today = new Date(3456, 6, 29);
    assertEquals(("3456/June/29"), today.toString());
  }

  // @Test
  // void toString_tc26() {
  //   assertThrows(
  //     IllegalArgumentException.class,
  //     () -> new Date(1600, -1, 30)
  //   );
  // }

  //@Test
  // void toString_tc27() {
  //   assertThrows(
  //     IllegalArgumentException.class,
  //     () -> new Date(1000, 12, 30)
  //   );
  // }

    @Test
    void  isLeapYear_tc26(){
      Date leapYear = new Date(1600, 2, 27);
      assertTrue(leapYear.isLeapYear());
    }

    
    @Test 
      void equals_tc27(){
      Date today = new Date(3456, 6, 29);
      assertFalse(today.equals(new String("Here")));
    }

    @Test 
    void equals_tc28(){
      Date day = new Date(3456, 6, 30);
      Date day1 = new Date(356, 5, 29);
      assertFalse(day.equals(day1));
    }

    @Test
    void equals_tc29(){
      Date day = new Date(3456, 6, 30);
      Date day1 = new Date(3456, 5, 30);
      assertFalse(day.equals(day1));
      
    }
    // @Test
    // void isEndOfTheMonth_tc30(){
    //   Date day = new Date(2323, 5, 30);  
    //   assertTrue(day.nextDate().equals(new Date(2323, 5, 31)));

    //   day = new Date(2323, 5, 31);  
    //   assertTrue(day.nextDate().equals(new Date(2323, 6, 1)));

    //   day = new Date(2020, 2, 29);  
    //   assertTrue(day.nextDate().equals(new Date(2020, 3, 1)));

    //   day = new Date(2021, 3, 30);  
    //   assertTrue(day.nextDate().equals(new Date(2021, 3, 31)));

    //   day = new Date(2021, 5, 28);  
    //   assertTrue(day.nextDate().equals(new Date(2021, 5, 29)));

    //   day = new Date(2020, 5, 27);  
    //   assertTrue(day.nextDate().equals(new Date(2020, 5, 28)));


    // }

}