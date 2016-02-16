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
	
	// run the KWIC app without any stop words
	public static void run (String lineFileName)
	{
		// Set the array of filters in the order that I wish to process them
		Filter[] filters = new Filter[] {new FileReader(), new CircularShifter(), new Sorter(), new FileWriter()};
		
		// add the file name for the lines to the arguments
		ArrayList<String> arguments = new ArrayList<String>();
		arguments.add(lineFileName);
		
		// set the input pipe for the first filter to include the arguments (file names).
		filters[0].setInPipe(new Pipe(arguments));
		
		// for each filter, create a new pipe, set the pipe as the current filter's output pipe, and the pipe as the next 
		// filter's input pipe
		// so it looks like this:
		//		filter[i] -> pipe -> filter[i+1]
		for (int i = 0; i < filters.length - 1; i++)
		{
			Pipe pipe = new Pipe();
			filters[i].setOutPipe(pipe);
			filters[i+1].setInPipe(pipe);
		}
		
		// run each filter
		for (Filter filter : filters)
			filter.run();
	}
	
	// run the KWIC app with stop words
	public static void run (String lineFileName, String stopWordFileName)
	{
		Filter[] filters = new Filter[] {new FileReader(), new CircularShifter(), new Sorter(), new FileWriter()};
		
		// add the file name for the lines and file name for stop words to the arguments
		ArrayList<String> arguments = new ArrayList<String>();
		arguments.add(lineFileName);
		arguments.add(stopWordFileName);
		
		// set the input pipe for the first filter to include the arguments (file names).
		filters[0].setInPipe(new Pipe(arguments));
		
		// for each filter, create a new pipe, set the pipe as the current filter's output pipe, and the pipe as the next 
		// filter's input pipe
		// so it looks like this:
		//		filter[i] -> pipe -> filter[i+1]
		for (int i = 0; i < filters.length - 1; i++)
		{
			Pipe pipe = new Pipe();
			filters[i].setOutPipe(pipe);
			filters[i+1].setInPipe(pipe);
		}
		
		for (Filter filter : filters)
			filter.run();
	}

	// this function converts an array list of strings to a string (concatenates all strings together)
	public static String arrayListToString(ArrayList<String> list)
	{
		String string = "";
		
		// concatenate all elements of the array together with a space in between (creating a sentence)
		for (int i = 0; i < list.size(); i++)
		{
			if (i == 0)
				string = list.get(i);
			else
				string = string + " " + list.get(i);
		}
		
		return string;
	}
	
	public static void main(String[] args)
	{
		// If there are 2 arguments, there is no stop word file
		if (args.length == 2)
		{
			if (args[0].equalsIgnoreCase("-f"))
			{
				KWICApp.run(args[1]);
				
				return;
			}
		}
		// If there are 4 arguments, there is a stop word file
		else if (args.length == 4)
		{
			int sIndex = -1; // index for the stopfile
			int fIndex = -1; // index for the file containing the lines
			int runningIndex = 0; // index for the current loop
			
			for (String s : args)
			{
				if (s.equalsIgnoreCase("-s"))
					sIndex = runningIndex + 1; // if the parameter is -f, we assume the file name is the next arg
				else if (s.equalsIgnoreCase("-f"))
					fIndex = runningIndex + 1; // if the parameter is -f, we assume the safeword file name is the next arg
				
				runningIndex = runningIndex + 1;
			}
			
			if (!(sIndex == -1 || fIndex == -1))
			{
				KWICApp.run(args[fIndex], args[sIndex]);
				
				return;
			}
	
		}
		
		System.out.println("Invalid arguments.");
		System.out.println("Please call the program by using:");
		System.out.println("\t java KWICApp -f myfile.text (-s stopwords.txt)");
	}
}
