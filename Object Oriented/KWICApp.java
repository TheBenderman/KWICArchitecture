// KWICApp.java
//
// Written by : Nathan Bender
//
// Purpose:
// Main driver class for the KWIC application. Reads the initial parameters for the program provided by the user.
// Determines if the user wishes to provide a list of stop words or not. Calls each of the other classes that perform
// their duties for the KWIC program. Also does some basic error checking for program parameters.

import java.util.ArrayList;

public class KWICApp {
	
	public KWICApp()
	{
		
	}
	
	public void run (String lineFileName)
	{
		FileReader fileReader = new FileReader();
		ArrayList<ArrayList<String>> parsedLines = fileReader.getLines(lineFileName); //Get the lines from the file
		
		if (parsedLines == null) // if there are no lines in the file, just return. Probably an error
			return;
		
		Lines lines = new Lines(parsedLines);
		
		CircularShifter circularShifter = new CircularShifter(lines);
		circularShifter.createCircularShifts(); // create all combinations of circularly shifted lines
		
		Sorter sorter = new Sorter(circularShifter);
		sorter.alphabatizeLines(); // sort the lines by value
		
		FileWriter fileWriter = new FileWriter(sorter.getSortedLines());
		fileWriter.writeLinesToFile(); // write the output to a file
	}
	
	public void run (String lineFileName, String stopWordFileName)
	{
		FileReader fileReader = new FileReader();
		ArrayList<ArrayList<String>> parsedLines = fileReader.getLines(lineFileName); // read the lines from a file
		ArrayList<String> stopWords = fileReader.getStopWords(stopWordFileName); // read the stop words from a file
		
		if (parsedLines == null || stopWords == null) // if there are no lines in the file, just return. Probably an error
			return;
		
		Lines lines = new Lines(parsedLines);
		
		CircularShifter circularShifter = new CircularShifter(lines);
		circularShifter.createCircularShifts(); // create all combinations of circularly shifted lines
		
		Sorter sorter = new Sorter(circularShifter, stopWords);
		sorter.alphabatizeLines(); // sort the lines, and remove stop words
		
		FileWriter fileWriter = new FileWriter(sorter.getSortedLines());
		fileWriter.writeLinesToFile(); // write the output to a file
	}

	public static void main(String[] args)
	{
		// If there are 2 arguments, there is no stop word file
		if (args.length == 2)
		{
			// Make sure the parameter supplied is -f
			if (args[0].equalsIgnoreCase("-f"))
			{
				KWICApp kwicapp = new KWICApp();
				kwicapp.run(args[1]);
				
				return;
			}
		}
		// If there are 4 arguments, there is a stop word file
		else if (args.length == 4)
		{
			int sIndex = -1; // index for the stopfile
			int fIndex = -1; // index for the file containing the lines
			int runningIndex = 0; // index for the current loop
			
			// search for the -s and -f flags, and then we know the specified file name is the next parameter
			// e.g: if the first parameter is -f, then the next parameter is the file name
			for (String s : args)
			{
				if (s.equalsIgnoreCase("-s"))
					sIndex = runningIndex + 1; // if the parameter is -f, we assume the file name is the next arg
				else if (s.equalsIgnoreCase("-f"))
					fIndex = runningIndex + 1; // if the parameter is -f, we assume the safeword file name is the next arg
				
				runningIndex = runningIndex + 1;
			}
			
			if (!(sIndex == -1 || fIndex == -1)) // if both the -f and -s flags are available, run kwic
			{
				KWICApp kwicapp = new KWICApp();
				kwicapp.run(args[fIndex], args[sIndex]);
				
				return;
			}
	
		}
		
		System.out.println("Invalid arguments.");
		System.out.println("Please call the program by using:");
		System.out.println("\t java KWICApp -f myfile.text (-s stopwords.txt)");
	}
}
