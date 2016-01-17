// Lines.java
//
// Written by : Nathan Bender
//
// Purpose:
// Class used for the storage of the lines. This class provides functions in order to set and get specific lines, as well as
// being able to get all of the lines as a collection. Also provides the ability to add a line to the list. Finally,
// this class allows for the removal of all lines that start with a stop word in the value.

import java.util.ArrayList;

public class Lines {
	private ArrayList<ArrayList<String>> lines;
	
	public Lines()
	{
		lines = new ArrayList<ArrayList<String>>();
	}
	
	// copy constructor allowing for intial lines to be copied
	public Lines(ArrayList<ArrayList<String>> copyLines)
	{
		lines = copyLines;
	}
	
	// get all of the lines stored
	public ArrayList<ArrayList<String>> getLines()
	{
		return lines;
	}
	
	// add a line to the collection
	public void addLine(ArrayList<String> line)
	{
		lines.add(line);
	}
	
	// update a specific line with a specific value
	public void setLine(ArrayList<String> line, int index)
	{
		lines.set(index, line);
	}
	
	// get a specific line with an index
	public ArrayList<String> getLine(int index)
	{
		return lines.get(index);
	}
	
	// remove all of the stop words from the collection
	public void removeStopWords(ArrayList<String> stopWords)
	{
		ArrayList<ArrayList<String>> tempLines = new ArrayList<ArrayList<String>>();
		
		// Add each line that does not start with a stop word to a temporary collection
		for (ArrayList<String> line : lines)
		{
			String value = line.get(1);
			if (!stopWords.contains(value.toLowerCase()))
				tempLines.add(line);
		}
		
		lines = tempLines; // copy the temporary collection to the list of lines
	}
}
