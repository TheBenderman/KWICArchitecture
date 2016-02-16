// FileReader.java
//
// Written by : Nathan Bender
//
// Purpose:
// Class for the purpose of reading the lines and stopwords from specified files. These file names are inputted through
// parameters of the program and passed to this class. Provides simple error checking for if the files do not exist or 
// there are errors reading from the files.

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReader extends Filter{
	
	public FileReader()
	{
	}
	
	// run the filter
	public void run()
	{
		ArrayList<String> arguments = inPipe.read(); // read the arguments from the inpipe
		ArrayList<String> lines = new ArrayList<String>();
		
		if (arguments.size() == 1) // if there is one argument, there are no stop words
		{
			lines.addAll(getLines(arguments.get(0))); // add all of the lines to the collection
		}
		else if (arguments.size() == 2) // if there are two arguments, there are stop words
		{
			lines.addAll(getLines(arguments.get(0))); // add all of the lines to the collection
			lines.add("#_STOP_WORDS"); // add the stop words delimeter to the collection
			lines.addAll(getStopWords(arguments.get(1))); // add all of the stop words to the collection
		}
		
		outPipe.write(lines);
	}
	
	// get all of the lines from the file and return an array with them
	public ArrayList<String> getLines(String fileName)
	{
		ArrayList<String> lines = new ArrayList<String>();
		
		try
		{
			// Read all of the lines from the file
			List<String> linesList = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			
			for (String line : linesList) // Add each line to the array
			{
				if(line != null && !line.isEmpty()) // Don't add the line if it is null
					lines.add(line); // Assume that each word is separated by a space
			}
		}
		catch(FileNotFoundException fNF)
		{
			System.out.println("Error! File: " + fileName + " not found!"); // Return if the file is not found
			return null;
		}
		catch(IOException io)
		{
			System.out.println("Error reading file: " + fileName + "!"); // Return if there is an error reading the file
			return null;
		}
		
		return lines;
	}
	
	// read all of the stop words from the file
	public ArrayList<String> getStopWords(String fileName)
	{
		ArrayList<String> stopWords = new ArrayList<String>();
		
		try
		{
			// Read all of the lines to the file
			List<String> linesList = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			
			for (String line : linesList)
			{
				if(line != null && !line.isEmpty()) // Do not add line to array if it is null
					stopWords.add(line.toLowerCase()); // Add the lower cased string to the array
			}
		}
		catch(FileNotFoundException fNF)
		{
			System.out.println("Error! File: " + fileName + " not found!"); // return null if the file is not found
			return null;
		}
		catch(IOException io)
		{
			System.out.println("Error reading file: " + fileName + "!"); // return null if there is an error reading the file
			return null;
		}
		
		return stopWords;
	}
}
