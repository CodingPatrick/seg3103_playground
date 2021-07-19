## Problem

The date of Easter in a given year is March (22 + A + B), where A and B
are determined as follows:

* A = the remainder of (19C + 24) / 30
* B = the remainder of (2D + 4E + 6A + 5) / 7
* C = the remainder of (year / 19)
* D = the remainder of (year / 4)
* E = the remainder of (year / 7)

This approach works for any year between 1854 and 4098 (inclusive). It computes an integer
date value which is the day in March for Easter. However, if the result is one
of the years 1954, 1981, 2049, or 2076, then the computed date value must be
reduced by 7. And, if the date value is > 31, then the result is a day in April,
so the date is calculated as date-31.

The easter program uses the class `EasterCalculator` method `easterDate`
to calculate the easter date given a year.
The header of the method is as follow:

```java
public static MyDate easterDate(int year)
// returns a date corresponding to the easter day of
// the year given if  1583 <= year <= 4099
// returns null if not
```

### Easter Q1:

Using the Equivalence Class Partitioning and the Boundary Value Analysis approaches,
design black box tests for the method easterDate. Show:

* your equivalence classes with a short descriptive note
* the boundary values that should be checked.

| EC | Input Condiiton | Valid EC | Invalid EC | Boundary Values | Description |
| --- | --- | --- | --- | --- | --- |
| &nbsp; | &nbsp; | &nbsp; | &nbsp; | &nbsp; | &nbsp; |


### Easter Q2

Write enough test cases to cover all the equivalence classes, and boundary values identified
in question Easter Q1

Provide a table showing the link between your test data and the equivalence classes.
This table should have the following format:

| Test Case Number | Test Data | Expected Results | Cover Equivalence | Boundary Values |
| --- | --- | --- | --- | --- |
| &nbsp; | &nbsp; | &nbsp; | &nbsp; |

You can find the dates of Easter at
https://tlarsen2.tripod.com/anthonypolumbo/apeasterdates.html

### Easter Q3

Implement your test suite using JUnit and the provided `EasterCalculatorTest`.
You are required to hand the source code of your test suite.

### Easter Q4

Report your test results by providing a table with the following format
(the test case numbers correspond to the ones in Easter Q2).

| Test Case Number | Expected Results | Actual Results | Verdict (pass/fail) |
| --- | --- | --- | --- |
| &nbsp; | &nbsp; | &nbsp; | &nbsp; |

Consider outputting this table as part of your JUnit code in Q3 above using Markdown.
