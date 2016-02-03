// CircularShifter.java
//
// Written by : Nathan Bender
//
// Purpose:
// Create all of the possible circular shifts for the collection of lines. Saves all of the circularly shifted lines to a collection
// to be used by the program output.

import java.util.ArrayList;
import java.util.Arrays;

public class CircularShifter extends Filter{
	
	public CircularShifter()
	{
		
	}
	
	public void run()
	{
		ArrayList<String> lines = inPipe.read();
		ArrayList<String> shiftedLines = createCircularShifts(lines);
		outPipe.write(shiftedLines);
	}
	
	// create all possible circular shifts
	public ArrayList<String> createCircularShifts(ArrayList<String> lines)
	{
		boolean foundStopWords = false;
		ArrayList<String> shiftedLines = new ArrayList<String>();
		
		// iterate over each line
		for (String stringLine : lines)
		{
			if (stringLine.equals("#_STOP_WORDS"))
			{
				foundStopWords = true;
			}
			
			ArrayList<String> line = new ArrayList(Arrays.asList(stringLine.split(" ")));
			
			if (!foundStopWords)
			{
				ArrayList<String> shiftedLine;
				
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
					
					shiftedLines.add(KWICApp.arrayListToString(shiftedLine)); // add the circularly shifted line to the collection
				}
			}
			else
			{
				shiftedLines.add(stringLine);
			}
		}
		
		return shiftedLines;
	}
}
