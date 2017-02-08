# conway-kata
Conway's game of life code kata

I did all of this in IntelliJ using Java/Angular. I will try to provide build/test methods 
that will work in a more general sense, but my knowledge is limited. I can't guarantee these 
steps willbe completely correct so please forgive me if you have any trouble with the 
instructions I have provided.

## Building The Project

1. Clone this repo into your workspace, and pull down the source code from the master branch.
2. Install the project using Maven (run the command: `mvn clean install`) this should include the following dependencies and plugins:
  * javax.servlet-api 3.0.1
  * jackson-core 2.8.6
  * jackson-databind 2.8.6
  * junit 4.12
  * maven-war-plugin 2.3
  * maven-compiler-plugin 3.1
  * tomcat7-maven-plugin 2.2

## Running The Project
1. Once installed, start the Java servlet with the tomcat7 plugin you just installed with `mvn tomcat7:run`
2. Open your browser and navigate to http://localhost:9090/conway you should see a 6x8 grid with several controls around it.
  * NOTE: If for some reason you cannot use port 9090, you can change this by replacing the number in `<port>9090</port>` of the tomcat plugin's configuration. You can find this in pom.xml
  
## Using The Interface
1. Click on any cell to change its state from dead to alive or vice-versa. White cells are dead, blue cells are alive.
2. Click the "Next" button to proceed one step forward to the next round of life.
3. Click on the "Start Animation" button to continuously step forward through rounds of life. This will continue until you hit the button again to stop the animation (even if the grid is blank or no more cells are changing)
4. To change the grid size, enter the desired number of rows and columns in the fields above the grid and hit "New Grid". This will create a new blank (dead) grid of the selected size and stop any previously started animiation.
  
## Testing The Project
*The unit tests provided here are admittedly not of the highest quality. I test some of the more internal functions but not the overall doGet() and doPost() methods of the servlet. I initially had created tests for these but the only way I was able to make them work was by creating mocks for basically every step of the proces, so nothing "real" was being tested except that the mocks I created returned the values that I told them to. I decided that wasn't as useful and decided to go for the partial tests instead.*

1. When you ran `mvn clean install` the output should have shown the passing unit tests. If you want to run the tests by themselves, enter the command: `mvn test`



