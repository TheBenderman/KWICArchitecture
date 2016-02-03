// Sorter.java
//
// Written by : Nathan Bender
//
// Purpose:
// Sort the values for each of the lines. This class uses the bubble sort algorithm in order to sort the values of the
// lines alphabetically. This class also calls the line class in order to remove the stop words from the collection.

import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.Line;

public class Sorter extends Filter {
	private ArrayList<ArrayList<String>> lines;
	private ArrayList<String> stopWords;
	
	public void run()
	{
		lines = new ArrayList<ArrayList<String>>();
		stopWords = new ArrayList<String>();
		
		ArrayList<String> input = inPipe.read();
		readLinesAndStopWords(input);
		removeStopWords();
		alphabatizeLines();
		
		outPipe.write(convertEachLineToString());
	}
	
	public void readLinesAndStopWords(ArrayList<String> input)
	{
		boolean stopWordsFound = false;
		for(int i = 0; i < input.size(); i++)
		{
			if (input.get(i).equals("#_STOP_WORDS"))
			{
				stopWordsFound = true;
				continue;
			}
			
			if (stopWordsFound)
				stopWords.add(input.get(i));
			else
			{
				lines.add(new ArrayList<String>(Arrays.asList(input.get(i).split(" "))));
			}
		}
	}

	// Sort each of the lines
	public void alphabatizeLines()
	{
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
	}
	
	public void removeStopWords()
	{
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
	}
	
	public ArrayList<String> convertEachLineToString()
	{
		ArrayList<String> linesAsStrings = new ArrayList<String>();
		
		for (ArrayList<String> line : lines)
		{
			linesAsStrings.add(KWICApp.arrayListToString(line));
		}
		
		return linesAsStrings;
	}
}
