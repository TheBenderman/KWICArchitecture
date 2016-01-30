// Sorter.java
//
// Written by : Nathan Bender
//
// Purpose:
// Sort the values for each of the lines. This class uses the bubble sort algorithm in order to sort the values of the
// lines alphabetically. This class also calls the line class in order to remove the stop words from the collection.

import java.util.ArrayList;

public class Sorter {
	private CircularShifter cShifter;
	private Lines sortedLines;
	private ArrayList<String> stopWords;
	
	// constructor if there are no stop words
	public Sorter(CircularShifter shifter)
	{
		cShifter = shifter;
		sortedLines = new Lines();
	}
	
	// contructor for if stop words are supplied
	public Sorter(CircularShifter shifter, ArrayList<String> sWords)
	{
		cShifter = shifter;
		sortedLines = new Lines();
		stopWords = sWords;
	}
	
	// Sort each of the lines
	public void alphabatizeLines()
	{
		Lines possibleLines = cShifter.getShiftedLines();
		
		// Using bubble sort to sort the lines
		for (int i = 0; i < possibleLines.getLines().size(); i++)
		{
			for (int j = 1; j < possibleLines.getLines().size(); j++)
			{
				String first = possibleLines.getLine(j).get(0); // get the key for the j-th index
				String second = possibleLines.getLine(j-1).get(0); // get the key for the (j-1)-th index
				
				// Compare the keys of the two elements. If the first is less than the second, swap them
				if (first.toLowerCase().compareTo(second.toLowerCase()) < 0)
				{
					// Swap the two values
					ArrayList<String> tempLine = possibleLines.getLine(j-1); // save j-1 to a temporary variable
					possibleLines.setLine(possibleLines.getLine(j), j-1); // overwrite the (j-1)-th index with the value from the j-th index
					possibleLines.setLine(tempLine, j); // overwrite the j-th index with the value from the temp variable
				}
			}
		}
		
		if (stopWords != null)
		{
			possibleLines.removeStopWords(stopWords);
		}
		
		sortedLines = possibleLines;
	}
	
	// get the collection of sorted lines
	public Lines getSortedLines()
	{
		return sortedLines;
	}
}
