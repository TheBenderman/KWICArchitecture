# KWICArchitecture
Implementation of the KWIC application in multiple architectures

# Overview of Application
The purpose of KWIC is to find the keywords in various input lines, find their context, and then stort them alphabetically. Each of these keywords is used as the key to be sorted alphabetically.

# How to run
	javac KWICApp.java #compiles all of the files
	java KWICApp -f inputfile.txt #runs the KWICApp with an input file (-f) and no stop words
	java KWICApp -f inputfile.txt -s stopwords.txt #runs the KWICApp with an input file (-f) and stop words file (-s)
