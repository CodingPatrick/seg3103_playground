# Assignment 2 - SEG 3103 Playground

### Team

Name: Patrick Loranger, plora079@uottawa.ca<br>
Student Number: 300112374<br>

Name: Akram El-Gaouny, aelga098@uottawa.ca<br>
Student Number: 300109692

### Professor and Teaching Assistant

Professor: Andrew Forward, aforward@uottawa.ca<br>

Course: SEG 3103<br>
Date: Thursday June 10, 2021

### Link for deliverable

* [https://github.com/CodingPatrick/seg3103_playground](https://github.com/CodingPatrick/seg3103_playground)
* The pdf file of the screencapture is found in the submission folder in Brightspace

## Problem 1

### Question 1.1
#### Draw the simplified control flow graph corresponding to each of the methods percentage_grade, letter_grade, and numeric_grade.

#### Percentage grade:

![precentage grade screenshot](assets/percentage_grade.png)

Please Note That The diagram below is based on the Line Number of the picture above.
At a condition Right mean True and Left Means False.

![precentage grade screenshot](assets/percentage_grade_diagram.png)

#### Letter grade:

![letter grade screenshot](assets/letter_grade.png)

Please Note That The diagram below is based on the Line Number of the picture above.
At a condition Right mean True and Left Means False.

![letter grade screenshot](assets/letter_grade_diagram.png)

#### Numeric grade:

![numeric grade screenshot](assets/numeric_grade.png)

Please Note That The diagram below is based on the Line Number of the picture above.
At a condition Right mean True and Left Means False.

![numeric grade screenshot](assets/numeric_grade_diagram.png)

### Question 1.2
#### Provide a white box test design for 100% branch coverage of	the methods percentage_grade, letter_grade, and numeric_grade. Your test suite will be evaluated	on the	number of its	test cases (try to have the smallest possible number of test cases	that will allow	 100% branch	coverage). Use the following template for	 your test case	design:

#### Notes for Reading the Table

* The Conditional Branches are Labeled in the corresponding control-flow diagrams above (Ex: B1, D2, etc.)
* For the conditions, I put the node where there’s a condition and what condition was evaluated. For example, (6-True) means that the condition in the if statement at line 6 of the code provided above was executed as true.
* Some conditions have multiple T/F, for example (45-True,True,True). What this means is that in line 45 there were three conditions and all of these conditions were true for a specific test

To see the complete table, please view pages 6 to 11 on in the following pdf document: [assignment2_problem1.pdf](assignment2_problem1.pdf)

### Question 1.3
#### Provide an Implementation of your test suite using ExUnit

Implemented in grades/test/grades/calculator_test.exs

The following screenshot shows that all the tests have passed for the Grades.Calculator Module.

![terminal screenshot](assets/terminal.png)

### Question 1.4:  
#### What is the degree of statement coverage obtained? If you weren’t able to achieve 100% coverage explain why. Please be sure to attach screenshots of your coverage results. Elixir’s coverage tool is primitive, as it only provides statement level accuracy. mix test --cover How might you address the limitations of a testing tool that only provides statement level coverage?

The degree of statement coverage that we obtained was 100% as we were able to cover all the statements in the three methods. We were able to achieve that since we were aiming to achieve a 100% percent branch coverage which implies a 100% statement coverage.

To address the limitation of a tool that gives you only statement coverage, you really need to analyze the relationship between the type of coverage you want to achieve and the statement coverage that was given by the testing tool. For example, we designed our tests to achieve a 100% branch coverage, so we would expect the result of the statement coverage to be 100%. If that is not the case, then we know that we didn’t achieve a 100% branch coverage, so you essentially get hints from the statement coverage tool and you use it to relate to the type of coverage you got.

Screenshots for coverages:

#### Percentage grade:

![coverage screenshot percentage](assets/coverage_percentage.png)

#### Letter grade: 

![coverage screenshot letter](assets/coverage_letter.png)

#### Numeric grade:

![coverage screenshot numeric](assets/coverage_numeric.png)

## Problem 2

### Question 2.1
#### Extract a helper method avg to clean up the duplicate code

* For the refactoring, see changed made on commit: [2.1](https://github.com/CodingPatrick/seg3103_playground/commit/c0cf11fda5dec44e1967f77384103ff19eaf61a3#diff-e4af7700659190dcf094daede863a09f5533c5e3d74c1eea4679f71c94c394c9)

Screenshot of helper method:

![question 2.1 helper methods](assets/problem2_question1.png)

### Question 2.2
#### Extract a helper method failed_to_participate? to clean up duplicate code

* For the refactoring, see changed made on commit: [2.2](https://github.com/CodingPatrick/seg3103_playground/commit/3a1ffdd0533ce0dba98caa200906af72381906fb#diff-e4af7700659190dcf094daede863a09f5533c5e3d74c1eea4679f71c94c394c9)

Screenshot of helper method:

![question 2.2 helper methods](assets/problem2_question2.png)

### Question 2.3
#### Extract a helper method calculate_grade to clean up duplicate code

* For the refactoring, see changed made on commit: [2.3 - 1](https://github.com/CodingPatrick/seg3103_playground/commit/1084c5545c07711eeb9d814dcfc1eaf75b674995#diff-e4af7700659190dcf094daede863a09f5533c5e3d74c1eea4679f71c94c394c9)
* We noticed we forgot to refactor one of the lines, see changes on commit: [2.3 - 2](https://github.com/CodingPatrick/seg3103_playground/commit/411dae4529a75881b8ff3735a06c5fa2a95d6c0d#diff-e4af7700659190dcf094daede863a09f5533c5e3d74c1eea4679f71c94c394c9)

Screenshot of helper method:

![question 2.3 helper methods](assets/problem2_question3.png)

### Question 2.4 
#### Provide at least 2 additional refactoring to the code. Your refactoring should not require additional testing, however if you encounter any bugs in the original code then please fix them separately (ensuring your tests continue to pass) before continuing to refactor.

* For the refactoring by the first helper method, see changed made on commit: [2.4 - 1](https://github.com/CodingPatrick/seg3103_playground/commit/0e30a057576bab30cf12fca4f5445d22e475ad1d#diff-e4af7700659190dcf094daede863a09f5533c5e3d74c1eea4679f71c94c394c9)
* For the refactoring by the second helper method, see changed made on commit: [2.4 - 2](https://github.com/CodingPatrick/seg3103_playground/commit/5f35b7a82c9922f8927e1bddd76eea1150e34943#diff-e4af7700659190dcf094daede863a09f5533c5e3d74c1eea4679f71c94c394c9)
* After refactoring with the first method, we thought it's better to generalize that method in terms of the name and parameters passed, See the changes on commit: [2.4 - 3](https://github.com/CodingPatrick/seg3103_playground/commit/5cf35b8baaa2fda4daa2de122d3cb8afea6483de)
* After refactoring with the second helper method, we found a better way to refactor the code. See the changes made on commit: [2.4 - 4](https://github.com/CodingPatrick/seg3103_playground/commit/70029cfb93dd8e84d08661af5d5bb9018400473f#diff-e4af7700659190dcf094daede863a09f5533c5e3d74c1eea4679f71c94c394c9)


Screenshot of helper method:

![question 2.4 helper methods](assets/problem2_question4.png)
