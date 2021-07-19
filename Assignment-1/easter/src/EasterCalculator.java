// Adapted from S. S. Somé
public class EasterCalculator {
  public static MyDate easterDate(int year) {
    // returns a date corresponding to the easter day of
    // the year given if  1583 < year < 4099
    // returns null if not
    // instance variables (object variables)
    int dayOfMonth;     // computed date value
    int a, b, c, d, e;  // variables used in formula for date calc.

    // Check for valid year
    // ERR1: should be year > 4098
    if ( year < 1584 || year > 4099 ) {
      return null;
    }

    // Calculate the terms for the date expression
    a = year % 19;
    b = year % 4;
    c = year % 7;
    d = (19 * a + 24) % 30;
    e = (2 * b + 4 * c + 6 * d + 5) % 7;

    // Calculate day of month expression, and adjust for the 4 special years
    dayOfMonth = 22 + d + e;
    // ERR2: missing year == 2049
    if ( year == 1954 || year == 1981 || year == 2076 ) {
      dayOfMonth = dayOfMonth - 7;
    }

    String month = "March";
    // ERR2: should be > 31
    if (dayOfMonth > 32) {
      month = "April";
      dayOfMonth = dayOfMonth - 31;
    }

    return new MyDate(month, dayOfMonth);
  }
}
