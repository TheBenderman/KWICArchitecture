Name: Nathan Bender
Class: SE 311 - Software Architecture II
Professor: Filipos Vokolos
Date: 2/16/16

All of the files for my program are in the root directory. Each of the files corresponds to a class of the KWIC application. The KWICApp.java file contains the main method of my program, which drives the function of the rest of the classes. My program uses the pipe and filter architectural style, which means that each class is a filter, and expects an input pipe and output pipe. The filter reads the input from the input pipe, parses the input, completes it's actions on the data, and then writes the data to the output pipe. Each of the files contains a single class. In order to run the program, make sure that the input and stopwords files are in same directory as the program files.

In order to run my program, simply use the following:
        javac KWICApp.java #compiles all of the files
        java KWICApp -f inputfile.txt #runs the KWICApp with an input file (-f) and no stop words
        java KWICApp -f inputfile.txt -s stopwords.txt #runs the KWICApp with an input file (-f) and stop words file (-s)

My program represents the pipe and filter architectural style because the each of the main functions of the program are filters (sorter, circularshifter...etc), and each filter transforms an input data stream into an output data stream. My program uses pipes to transport data between the functional classes. Each filter expects an input pipe (which it reads from) and output pipe (which it writes to). These pipes connect the class either to an upstream or downstream filter.

Following is a list of the functions of each of the classes:
        CircularShifter.java #creates all possible circular shifts of the input lines
        FileReader.java #reads the input lines from an input file. also reads stop words if a file has been provided
        FileWriter.java #writes the output of the program to a file
        Sorter.java #sorts the lines alphabetically using the key of the line
