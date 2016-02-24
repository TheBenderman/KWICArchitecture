// KWICApp.java
//
// Written by : Nathan Bender
//
// Purpose:
// Main driver class for the KWIC application. Reads the initial parameters for the program provided by the user.
// Determines if the user wishes to provide a list of stop words or not. Calls each of the other classes that perform
// their duties for the KWIC program. Also does some basic error checking for program parameters.

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KWICApp {
	
	private ArrayList<ArrayList<String>> data;
	
	public KWICApp()
	{
		data = new ArrayList<ArrayList<String>>();
	}
	
	public void getLinesFromFile(String filename)
	{
		// Read all of the lines from the file
		List<String> linesList;
		try {
			linesList = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
			
			for (String line : linesList) // Add each line to the array
			{
				if(line != null && !line.isEmpty()) // Don't add the line if it is null
					data.add(new ArrayList<String>(Arrays.asList(line.split(" ")))); // Assume that each word is separated by a space
			}
		}
		catch(FileNotFoundException fNF)
		{
			System.out.println("Error! File: " + filename + " not found!"); // Return if the file is not found
		}
		catch(IOException io)
		{
			System.out.println("Error reading file: " + filename + "!"); // Return if there is an error reading the file
		}
	}
	
	public void getStopWordsFromFile(String filename)
	{	
		try
		{
			// Read all of the lines to the file
			List<String> linesList = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
			
			ArrayList<String> stopwords = new ArrayList<String>();
			stopwords.add("#_STOP_WORDS");
			
			for (String line : linesList)
			{
				if(line != null && !line.isEmpty()) // Do not add line to array if it is null
					stopwords.add(line.toLowerCase()); // Add the lower cased string to the array
			}
			
			data.add(stopwords);
		}
		catch(FileNotFoundException fNF)
		{
			System.out.println("Error! File: " + filename + " not found!"); // return null if the file is not found
		}
		catch(IOException io)
		{
			System.out.println("Error reading file: " + filename + "!"); // return null if there is an error reading the file
		}
	}
	
	public void createCircularShifts()
	{
		ArrayList<ArrayList<String>> shiftedLines = new ArrayList<ArrayList<String>>();
		// iterate over each line
		for (ArrayList<String> line : data)
		{	
			ArrayList<String> shiftedLine;
			
			if (line.size() > 0 && line.get(0).equals("#_STOP_WORDS"))
			{
				shiftedLines.add(line);
			}
			else
			{
				// create all of the circular shifts for the current line
				for(int i = 0; i < line.size(); i++)
				{
					shiftedLine = new ArrayList<String>();
	
					// wrap the array around if we are not starting at the first index
					// e.g
					// original : the force awakens
					// new : awakens the force
					for (int j = i; j < (i + line.size()); j++)
					{
						if (j >= line.size())
							shiftedLine.add(line.get(j-line.size()));
						else
							shiftedLine.add(line.get(j));
					}
					
					shiftedLines.add(shiftedLine); // add the circularly shifted line to the collection
				}
			}
		}
		
		data = shiftedLines;
	}
	
	public void sortLines()
	{
		ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
		ArrayList<String> stopWords = new ArrayList<String>();
		
		for(int i = 0; i < data.size(); i++)
		{
			// if we found the stop words, we no longer want to parse lines
			if (data.get(i).size() > 0 && data.get(i).get(0).equals("#_STOP_WORDS"))
			{
				for (int j = 0; j < data.get(i).size(); j++)
				{
					if (!data.get(i).get(j).equals("#_STOP_WORDS"))
						stopWords.add(data.get(i).get(j));
				}
			}
			else
				lines.add(data.get(i));
		}
		
		// Using bubble sort to sort the lines
		for (int i = 0; i < lines.size(); i++)
		{
			for (int j = 1; j < (lines.size()-i); j++)
			{
				String first = lines.get(j).get(0); // get the key for the j-th index
				String second = lines.get(j-1).get(0); // get the key for the (j-1)-th index
				
				// Compare the keys of the two elements. If the first is less than the second, swap them
				if (first.toLowerCase().compareTo(second.toLowerCase()) < 0)
				{
					// Swap the two values
					ArrayList<String> tempLine = lines.get(j-1); // save j-1 to a temporary variable
					lines.set(j-1, lines.get(j)); // overwrite the (j-1)-th index with the value from the j-th index
					lines.set(j, tempLine); // overwrite the j-th index with the value from the temp variable
				}
			}
		}
		
		// make sure we have stop words before trying
		if(stopWords != null && !stopWords.isEmpty())
		{
			ArrayList<ArrayList<String>> tempLines = new ArrayList<ArrayList<String>>();
			
			// Add each line that does not start with a stop word to a temporary collection
			for (ArrayList<String> line : lines)
			{
				String value = line.get(0);
				if (!stopWords.contains(value.toLowerCase()))
					tempLines.add(line);
			}
			
			lines = tempLines; // copy the temporary collection to the list of lines
		}
		
		data = lines;
	}
	
	public void writeToFile()
	{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"))) 
		{		
			writer.write("Key\t\t\t\tValue\n");
			writer.write("-------------------------------------------------\n");
			
			// For each of the lines, write the key and value to the file
			for(ArrayList<String> line : data)
			{	
				writer.write(line.get(0) + "\t\t\t\t"); // write the key to the line
				
				String valueString = "";
				for (int i = 1; i < line.size(); i++) // create a string for the value
				{
					valueString = valueString + line.get(i) + " ";
				}
				writer.write(valueString + "\n\n"); // write the value to the file
			}
		}
		catch (FileNotFoundException fNF)
		{
			System.out.println("Error writing to file : output.txt!");
		}
		catch (Exception ex)
		{
			
		}
	}
	
	public static void main(String[] args)
	{
		KWICApp kwic = new KWICApp();
		// If there are 2 arguments, there is no stop word file
		if (args.length == 2)
		{
			if (args[0].equalsIgnoreCase("-f"))
			{
				kwic.getLinesFromFile(args[1]);
				kwic.createCircularShifts();
				kwic.sortLines();
				kwic.writeToFile();
				
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
				kwic.getLinesFromFile(args[fIndex]);
				kwic.getStopWordsFromFile(args[sIndex]);
				kwic.createCircularShifts();
				kwic.sortLines();
				kwic.writeToFile();
				
				return;
			}
	
		}
		
		System.out.println("Invalid arguments.");
		System.out.println("Please call the program by using:");
		System.out.println("\t java KWICApp -f myfile.text (-s stopwords.txt)");
	}
}
