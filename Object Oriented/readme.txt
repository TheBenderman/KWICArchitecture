Name: Nathan Bender
Class: SE 311 - Software Architecture II
Professor: Filipos Vokolos
Date: 1/28/16

All of the files for my program are in the root directory. Each of the files corresponds to a class of the KWIC application. The KWICApp.java file contains the main method of my program, which drives the function of the rest of the classes. Each of the files contains a single class. In order to run the program, make sure that the input and stopwords files are in same directory as the program files. 

In order to run my program, simply use the following:
	javac KWICApp.java #compiles all of the files
	java KWICApp -f inputfile.txt #runs the KWICApp with an input file (-f) and no stop words
	java KWICApp -f inputfile.txt -s stopwords.txt #runs the KWICApp with an input file (-f) and stop words file (-s)

My program represents the object oriented style because each of the classes in the project serves a specific function. For example, the CircularShifter.java file generates all of the possible circular shifts for the input file, and that is the only thing that it does. My code is loosely coupled, meaning that each of the classes only does the work that is related to that class.

Following is a list of the functions of each of the classes:
	CircularShifter.java #creates all possible circular shifts of the input lines
	FileReader.java #reads the input lines from an input file. also reads stop words if a file has been provided
	FileWriter.java #writes the output of the program to a file
	Lines.java #responsible for storing and modifying the lines of the program.
	Sorter.java #sorts the lines alphabetically using the key of the line
