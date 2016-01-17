import java.util.ArrayList;

public class CircularShifter {

	private Lines shiftedLines;
	private Lines originalLines;
	
	public CircularShifter(Lines lines)
	{
		originalLines = lines;
		shiftedLines = new Lines();
	}
	
	public Lines getShiftedLines()
	{
		return shiftedLines;
	}
	
	public void createCircularShifts()
	{
		for (ArrayList<String> line : originalLines.getLines())
		{
			ArrayList<String> shiftedLine;
			
			for(int i = 0; i < line.size(); i++)
			{
				shiftedLine = new ArrayList<String>();
				
				for (int j = i; j < (i + line.size()); j++)
				{
					if (j >= line.size())
						shiftedLine.add(line.get(j-line.size()));
					else
						shiftedLine.add(line.get(j));
				}
				
				shiftedLines.addLine(shiftedLine);
			}
		}
	}
}
