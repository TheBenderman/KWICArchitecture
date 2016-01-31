// CircularShifter.java
//
// Written by : Nathan Bender
//
// Purpose:
// Create all of the possible circular shifts for the collection of lines. Saves all of the circularly shifted lines to a collection
// to be used by the program output.
// 
// E.g: If the line is : The Force Awakens
// This class will create 3 possible lines:
// 	The Force Awakens
// 	Force Awakens The
// 	Awakens The Force

import java.util.ArrayList;

public class CircularShifter {

	private Lines shiftedLines;
	private Lines originalLines;
	
	public CircularShifter(Lines lines)
	{
		originalLines = lines;
		shiftedLines = new Lines();
	}
	
	// return all of the lines
	public Lines getShiftedLines()
	{
		return shiftedLines;
	}
	
	// create all possible circular shifts
	public void createCircularShifts()
	{
		// iterate over each line
		for (ArrayList<String> line : originalLines.getLines())
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
				
				shiftedLines.addLine(shiftedLine); // add the circularly shifted line to the collection
			}
		}
	}
}
