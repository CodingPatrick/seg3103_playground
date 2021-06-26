## Part 1: Stubbing Grades.Calculator

### Before Stubbing:
![image](https://user-images.githubusercontent.com/64698780/123427138-3bca7e80-d592-11eb-8ff3-064bbcf563c6.png)

We can clearly see that there is no module called Grades.Calculator and thus the web application is failing. So, to avoid the faliure ( or make it fail in a way that ensures that the web application is not failing due to an error in the web app code) we need to stub Grades.Calculator.
### After Stubbing:
Here's the stubs that were created

![image](https://user-images.githubusercontent.com/64698780/123430958-beedd380-d596-11eb-9b9e-72a4fdf9fd84.png)

Here's the effect of the stubs on the interface
![image](https://user-images.githubusercontent.com/64698780/123431051-d7f68480-d596-11eb-809f-9d029fa4d3d3.png)

Now, after stubbing we know that if there's a faliure in the application, it won't be from the web application but rather from another module. 

Since that we have tested the interface between the UI and the Grades.Calculator module, we can be sure that when we implement the actual logic in the Grades.Calculator module it won't fail due to an issue of communication (interface) between the UI and the module.

### Implementing The Actual Logic:

After implementing the actual logic from the A2 calculator module (As seen in this [commit](https://github.com/CodingPatrick/seg3103_playground/commit/66b560641375ab2db26701367a406c6014def3cd#diff-78d95c3f77e18667fedda5dfaad6f2fd7c0ee89048b3552bdeb43c7419f459a7)). The button and the logic don't work and I believe the reason it does not work is because of the addition operations. The interface (UI) calls the calculator functions with STRING parameters as opposed to int parameters therefore the A2 impleemntation is not compatiable with the web application. The error below is what I got when I implemented the logic of A2.

![image](https://user-images.githubusercontent.com/64698780/123523896-e4610700-d694-11eb-8671-ef4b9fe351d4.png)
