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
	
	public static void run (String lineFileName)
	{
		Filter[] filters = new Filter[] {new FileReader(), new CircularShifter(), new Sorter(), new FileWriter()};
		
		ArrayList<String> arguments = new ArrayList<String>();
		arguments.add(lineFileName);
		
		filters[0].setInPipe(new Pipe(arguments));
		
		for (int i = 0; i < filters.length - 1; i++)
		{
			Pipe pipe = new Pipe();
			filters[i].setOutPipe(pipe);
			filters[i+1].setInPipe(pipe);
		}
		
		for (Filter filter : filters)
		{
			filter.run();
		}
		
		/* 
		Pipe inPipe = new Pipe(arguments);
		Pipe outPipe = new Pipe();
		
		for (int i = 0; i < filters.length - 1; i++)
		{
			outPipe = new Pipe();
			filters[i].setInPipe(inPipe);
			filters[i].setOutPipe(outPipe);
			
			filters[i].run();
			
			inPipe = outPipe;
		}
		 */
	}
	
	public static void run (String lineFileName, String stopWordFileName)
	{
		Filter[] filters = new Filter[] {new FileReader(), new CircularShifter(), new Sorter(), new FileWriter()};
		
		ArrayList<String> arguments = new ArrayList<String>();
		arguments.add(lineFileName);
		arguments.add(stopWordFileName);
		filters[0].setInPipe(new Pipe(arguments));
		
		for (int i = 0; i < filters.length - 1; i++)
		{
			Pipe pipe = new Pipe();
			filters[i].setOutPipe(pipe);
			filters[i+1].setInPipe(pipe);
		}
		
		for (Filter filter : filters)
		{
			filter.run();
		}
	}

	public static String arrayListToString(ArrayList<String> list)
	{
		String string = "";
		
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
