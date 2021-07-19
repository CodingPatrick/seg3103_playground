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
![terminal screenshot](Assets/CompiledPicture.PNG)

### Compiling the tests

```code
javac -encoding UTF-8 --source-path test -d dist -cp dist:lib/junit-platform-console-standalone-1.7.1.jar test/*.java
```
![terminal screenshot](Assets/compiledTests.PNG)

### Running the Application

```code
java -cp ./dist Main
```
![terminal screenshot](Assets/ApplicationRunning.PNG)

### Running Spotbugs For Analysis

```code
java -jar ./lib/spotbugs/lib/spotbugs.jar
```
![terminal screenshot](Assets/SpotBugs.PNG)

### Fixing/Suggesting Bug Fixes:

#### Bug 1: 

For this bug, the original code uses == to compare strings and that is incorrect because String is a reference type not a primitive types

##### Spot Bug Description
![terminal screenshot](Assets/Bug1/Bug1Description.PNG)
##### The Code Before Fixing The Issue
![terminal screenshot](Assets/Bug1/Bug1Before.PNG)
##### The Code After Fixing the Issue
![terminal screenshot](Assets/1/Bug1After.PNG)



