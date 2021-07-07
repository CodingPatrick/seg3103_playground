
# Lab 04 - SEG 3103 Playground

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



### Compiling Maven

After installing maven run the following command
``` code 
mvn compile
```

Here is a screenshot of the successful compiling using maven
![image](https://user-images.githubusercontent.com/64698780/124795224-d1182c00-df1d-11eb-90db-910bb3e16ee8.png)


### Packaging the bookstoreApp

After comiling maven run the follwoing command
``` code 
mvn package -DskipTests
```

The command will download a number of files, here's a proof that I ran this command (beginning and end of the run).
#### Beginning of the run
![image](https://user-images.githubusercontent.com/64698780/124796160-d88c0500-df1e-11eb-977d-48a0d7c45004.png)
### End of the run
![image](https://user-images.githubusercontent.com/64698780/124796271-f9545a80-df1e-11eb-9b70-aa92d38d2e4d.png)


### Running The Server and Viewing The App

#### Homepage
![image](https://user-images.githubusercontent.com/64698780/124803013-94046780-df26-11eb-9cdb-a65969396ab8.png)

#### Admin Page
![image](https://user-images.githubusercontent.com/64698780/124803088-a9799180-df26-11eb-8cb1-88c329febda4.png)

### Running The Tests
The following command was run to execute the default tests
``` code 
mvn test
```

![image](https://user-images.githubusercontent.com/64698780/124804009-cebacf80-df27-11eb-93b5-88701723b383.png)

