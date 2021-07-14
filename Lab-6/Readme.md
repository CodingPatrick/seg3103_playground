# Lab 06 - SEG 3103 Playground

### Team

Name: Patrick Loranger, plora079@uottawa.ca<br>
Student Number: 300112374<br>

Name: Akram El-Gaouny, aelga098@uottawa.ca<br>
Student Number: 300109692

### Professor and Teaching Assistant

Professor: Andrew Forward, aforward@uottawa.ca<br>
TA: Nazanin Bayati Chaleshtari, nbaya076@uottawa.ca<br>

Course: SEG 3103<br>
Date: Thursday June 10, 2021

### Link for deliverable

* [https://github.com/CodingPatrick/seg3103_playground](https://github.com/CodingPatrick/seg3103_playground)
* The pdf file of the screencapture is found in the submission folder in Brightspace

### Compiling Maven

First I run this command line:
```code
mvn --version
```
And I get this output:
![mvn version output](assets/MVN_version.png)

After installing maven run the following command
``` code 
mvn compile
```
Here is a screenshot of the successful compiling using maven:
![mvn compile](assets/MVN_compile.png)

### Packaging the bookstoreApp

After comiling maven run the follwoing command
``` code 
mvn package -DskipTests
```

The command will download a number of files, here's a proof that we ran this command (beginning and end of the run).
#### Beginning of the run
![mvn skip 1](assets/MVN_skip1.png)
### End of the run
![mvn skip 2](assets/MVN_skip2.png)

### Running The Server and Viewing The App

Once everything is downloaded and ready, I run this command line to open the localhost server:
```code
java -jar ./target/BookstoreApp-0.1.0.jar
```
#### Homepage
![homepage](assets/localhost1.png)

#### Admin Login
![admin login](assets/localhost2.png)

#### Admin Page
![admin page](assets/localhost3.png)

### Running The Tests
The following command was run to execute the default tests
``` code 
mvn test
```
![tests before](assets/MVN_test_before.png)

After initially running the tests, I added a new selenium test to the code:
![selenium test](assets/SeleniumTest.png)

Finally, I ran the testing command line one more time to see if my new test passes:
![tests before](assets/MVN_test_after.png)
It passed and it works.