# AI1a-Missionaries and Cannibals

This is a university AI programming assignment to solve the missionaries and cannibals problem using A*

COMPILE:  
      `javac -sourcepath <directory>/ <directory>/*.java`

e.g.  
    `javac -sourcepath ./src/ ./src/*.java`  
    `javac -sourcepath src/ src/*.java`  
    `javac *.java` (if already in src directory)  
  
RUN:  
    `java -cp <directory> Main <arguments>`  
    `java Main <arguments>` (if already in src directory)  
  
Arguments:  
	    - int population(number of cannibals/missionaries on left bank)  
	    - int boatCapacity (number of people the boat can carry)  
	    - int maxCrosses (maximum number of crosses the boat can attempt)  
	    - without arguments defaults: population = 3, boatCapacity = 2, maxCrosses = 11  
  
e.g.   
    `java -cp ./src Main 100 4 1000`  
        results in (100 missionaries, 100 cannibals, 4 people boat capacity, 1000 max crosses)  
    `java -cp ./src Main`  
        results in (default 3 missonaries, 3 cannibals, 2 people boat capacity, 11 max crosses)  
