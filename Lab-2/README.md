# Lab 02 - SEG 3103 Playground

### Team

Name: Patrick Loranger, plora079@uottawa.ca<br>
Student Number: 300112374<br>

Name: Akram El-Gaouny, aelga098@uottawa.ca<br>
Student Number: 300109692

### Professor and Teaching Assistant

Professor: Andrew Forward, aforward@uottawa.ca<br>
TA: Henry Chen, zchen229@uottawa.ca<br>

Course: SEG 3103<br>
Date: Thursday May 27, 2021

### Link for deliverable

* [https://github.com/CodingPatrick/seg3103_playground](https://github.com/CodingPatrick/seg3103_playground)
* The pdf file of the screencapture is found in the submission folder in Brightspace

## Exercise 1

To run the application, we entered the following command line
```bash
java -jar  user-registration-app-0.1.0.jar
```
Then we visit the Local Host website.
![local host screenshot](assets/LocalHost.PNG)

After we fill out the table with Test Cases, Results and the Verdict

| Test Case # | Test Case | Expected Result | Actual Result | Verdict (Pass, Fail, Inconclusive) |
| --- | --- | --- | --- | --- |
| 1 | UserName: A00000<br>FirstName: ""<br>LastName: ""<br>Age: 18<br>Email:+@-.AA<br>City: Halifax<br>PostalCode: A0A0A0 | registration request accepted | registration request accepted | Pass |
| 2 | UserName: zzzzzzzzzzzz<br>FirstName: A<br>LastName: zzzzzzzzzzzzzzzzz<br>Age: 64<br>Email:zzZZz.zz999@zzz.zzzz99999zzzzzzz.zzzzz zz9999zzzzz.zzzzzz<br>City: Toronto<br>PostalCode: Y9Z9Z9 | registration request accepted | registration request accepted | Pass |
| 3 | UserName: Aure723xxz<br>FirstName: "A "<br>LastName: “Bob The Great”<br>Age: 35<br>Email:boByy4534@some.where.com<br>City: Ottawa<br>PostalCode: A9A 9A9 | registration request accepted | registration request accepted | Pass |
| 4 | UserName: X56565z0<br>FirstName: Bond<br>LastName: James<br>Age: 60<br>Email:jb007@mi6.org<br>City: Montreal<br>PostalCode: Y0Z 0Z0 | registration request accepted | registration request accepted | Pass |
| 5 | UserName: ""<br>FirstName: ""<br>LastName: ""<br>Age: 18<br>Email: +@-.AA<br>City: Halifax<br>PostalCode: A0A0A0 | Err1 | Err1 and Err3 | Fail |
| 6 | UserName: z<br>FirstName: A<br>LastName: zzzzzzzzzzzzzzzzz<br>Age: 64<br>Email: zzZZz.zz999@zzz.zzzz99999zzzzzzz.zzzzz zz9999zzzzz.zzzzzz<br>City: Toronto<br>PostalCode: Y9Z9Z9 | Err3 | Err1 and Err3 | Fail |
| 7 | UserName: zzzzz<br>FirstName: “A ”<br>LastName: “Bob The Great”<br>Age: 35<br>Email: boByy4534@some.where.com<br>City: Ottawa<br>PostalCode: A9A 9A9 | Err3 | Err3 | Pass |
| 8 | UserName: @adr278a<br>FirstName: Bond<br>LastName: James<br>Age: 60<br>Email: jb007@mi6.org<br>City: Montreal<br>PostalCode: Y0Z 0Z0 | Err1 | Err1 | Pass |

### Screenshots for Exercise 1
You can find screenshots under assets. Either Input_TCX.PNG or Output_TCX.PNG, where X is the Test Case Number.

| Test Case # | Input | Output |
| --- | --- | --- |
| 1 | ![test case 1 input screenshot](assets/Input_TC1.PNG) | ![test case 1 output screenshot](assets/Output_TC1.PNG) |
| 2 | ![test case 2 input screenshot](assets/Input_TC2.PNG) | ![test case 2 output screenshot](assets/Output_TC2.PNG) |
| 3 | ![test case 3 input screenshot](assets/Input_TC3.PNG) | ![test case 3 output screenshot](assets/Output_TC3.PNG) |
| 4 | ![test case 4 input screenshot](assets/Input_TC4.PNG) | ![test case 4 output screenshot](assets/Output_TC4.PNG) |
| 5 | ![test case 5 input screenshot](assets/Input_TC5.PNG) | ![test case 5 output screenshot](assets/Output_TC5.PNG) |
| 6 | ![test case 6 input screenshot](assets/Input_TC6.PNG) | ![test case 6 output screenshot](assets/Output_TC6.PNG) |
| 7 | ![test case 7 input screenshot](assets/Input_TC7.PNG) | ![test case 7 output screenshot](assets/Output_TC7.PNG) |
| 8 | ![test case 8 input screenshot](assets/Input_TC8.PNG) | ![test case 8 output screenshot](assets/Output_TC8.PNG) |

### Screenshot of Tests before Exercise 2
Here is a screenshot of the terminal after running the tests with the following command lines:
```bash
javac -encoding UTF-8 --source-path test -d dist -cp dist:lib/junit-platform-console-standalone-1.7.1.jar test/*.java
```
```bash
java -jar lib/junit-platform-console-standalone-1.7.1.jar --class-path dist --scan-class-path
```
![terminal screenshot](assets/E1_Terminal.PNG)

## Exercise 2

### Test case suite in the lab PowerPoint

In Exercise 2, we were tasked with implementing some explicit test cases and some parameterized test cases based on the following screenshot:<br>

![test case suite screenshot](assets/E2_TestCases.PNG)

These tests can be found under ecs/test. The file with the explicit tests is DateTest.java. The file with the parameterized tests that run OK is DateNextDateOKTest.java. The file with the parameterized test that result in an exception is DateNextDateExceptionTest.java.

### Screenshot of the Tests after Exercise 2
Here is a screenshot of the terminal after running the tests with the following command lines:
```bash
javac -encoding UTF-8 --source-path test -d dist -cp dist:lib/junit-platform-console-standalone-1.7.1.jar test/*.java
```
```bash
java -jar lib/junit-platform-console-standalone-1.7.1.jar --class-path dist --scan-class-path
```
![terminal screenshot](assets/E2_Terminal.PNG)
