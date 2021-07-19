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

## Fixing/Suggesting Bug Fixes:

### Bug 1: 

For this bug, the original code uses == to compare strings and that is incorrect because String is a reference type not a primitive types

##### Spot Bug Description
![terminal screenshot](Assets/Bug1/Bug1Description.PNG)
##### The Code Before Fixing The Issue
![terminal screenshot](Assets/Bug1/Bug1Before.PNG)
##### The Code After Fixing the Issue
![terminal screenshot](Assets/Bug1/Bug1After.PNG)



### Bug 2: 
For this bug, what is happening is that the original code calls the function setVisible outside of a 
##### Spot Bug Description
![terminal screenshot](Assets/Bug2/Bug2Description.PNG)
##### The Code Before Fixing The Issue
![terminal screenshot](Assets/Bug2/Bug2Before.PNG)
##### Proposed Soloution
For this bug, we need to make sure that the function JFrame.setVisible is placed inside a Java Swing thread not just in main.


# skipping Bug3 for now

### Bug 3: 
For this bug, what is happening is that the original code calls the function setVisible outside of a 
##### Spot Bug Description
![terminal screenshot](Assets/Bug3/Bug3Description.PNG)
##### The Code Before Fixing The Issue
![terminal screenshot](Assets/Bug3/Bug3Before.PNG)
##### Proposed Soloution
For this bug, we need to make sure that the function JFrame.setVisible is placed inside a Java Swing thread not just in main.

### Bug 4: 
For this bug, there is a method that is never being used by the application and it is just wasting memory. So I deleted it.
##### Spot Bug Description
![terminal screenshot](Assets/Bug4/Bug4Description.PNG)
##### The Code Before Fixing The Issue
![terminal screenshot](Assets/Bug4/Bug4Before.PNG)
##### Proposed Soloution
![terminal screenshot](Assets/Bug4/Bug4After.PNG)


### Bug 5: 
For this bug, .
##### Spot Bug Description
![terminal screenshot](Assets/Bug5/Bug5Description.PNG)
##### The Code Before Fixing The Issue
![terminal screenshot](Assets/Bug5/Bug5Before.PNG)
##### Proposed Soloution
![terminal screenshot](Assets/Bug5/Bug5After.PNG)


