Name: Nathan Bender
Class: SE 311 - Software Architecture II
Professor: Filipos Vokolos
Date: 3/1/16

All of the functionality of my program is in a single file in my submission called KWICApp.java. The KWICApp.java file contains the main method of my program, which drives the function of the program. Each of the methods in the file corresponds to a class in the previous architectural assignments. Each of these methods accesses a single shared data structure. My program uses the main program and subroutine with shared data architectural style, which means that the application is this single file, each functionality of the program is a separate method, and each method gets its input/sets its output from the shared data structure. My shared data structure is a 2D array list. In order to run the program, make sure that the input and stopwords files are in same directory as the program files.

In order to run my program, simply use the following:
        javac KWICApp.java #compiles all of the files
        java KWICApp -f inputfile.txt #runs the KWICApp with an input file (-f) and no stop words
        java KWICApp -f inputfile.txt -s stopwords.txt #runs the KWICApp with an input file (-f) and stop words file (-s)

My program represents the main program and subroutine with shared data architectural style because the each of the main functions of the program are methods in the KWICApp.java file, and are driven by the main program (the main function). Each of these methods perform the same functionality that the classes in the previous two assignments did (read from file, create circular shifts, sort the circular shifts, remove the stop words, and write output to a file). Each of the methods uses a shared data structure, meaning that a single data structure is used for all methods. Each of the methods reads from this data structure, performs some action, and then overwrites the data structure with the actions that it performed.
