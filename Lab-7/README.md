# Lab 07 - SEG 3103 Playground

### Team

Name: Patrick Loranger, plora079@uottawa.ca<br>
Student Number: 300112374<br>

Name: Akram El-Gaouny, aelga098@uottawa.ca<br>
Student Number: 300109692

### Professor and Teaching Assistant

Professor: Andrew Forward, aforward@uottawa.ca<br>
TA: Nazanin Bayati Chaleshtari, nbaya076@uottawa.ca<br>

Course: SEG 3103<br>
Date: Thursday July 22, 2021


### Proof of compiling the application

### Compiling the source Code

```code
javac -encoding UTF-8 --source-path src -d dist src/*.java

```
![terminal_screenshot_compilation_source](assets/CompiledPicture.png)

### Compiling the tests

```code
javac -encoding UTF-8 --source-path test -d dist -cp dist:lib/junit-platform-console-standalone-1.7.1.jar test/*.java
```
![terminal_screenshot_comilation_tests](assets/compiledTests.png)

### Running the Application

```code
java -cp ./dist Main
```
![App_Running](assets/ApplicationRunning.png)

### Running Spotbugs For Analysis

```code
java -jar ./lib/spotbugs/lib/spotbugs.jar
```
![BugsAppearing](assets/SpotBugs.png)

### Fixing/Suggesting Bug Fixes:

#### Bug 1: 

For this bug, the original code uses == to compare strings and that is incorrect because String is a reference type not a primitive types

##### Spot Bug Description
![Bug1Description](assets/Bug1/Bug1Description.png)
##### The Code Before Fixing The Issue
![Bug1Before](assets/Bug2/Bug1Before.png)
##### The Code After Fixing the Issue
![Bugs1After](assets/Bug2/Bug1After.png)



