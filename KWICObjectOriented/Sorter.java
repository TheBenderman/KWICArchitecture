import java.util.ArrayList;

public class Sorter {
	private CircularShifter cShifter;
	private Lines sortedLines;
	private ArrayList<String> stopWords;
	
	public Sorter(CircularShifter shifter)
	{
		cShifter = shifter;
		sortedLines = new Lines();
	}
	
	public Sorter(CircularShifter shifter, ArrayList<String> sWords)
	{
		cShifter = shifter;
		sortedLines = new Lines();
		stopWords = sWords;
	}
	
	public void alphabatizeLines()
	{
		Lines possibleLines = cShifter.getShiftedLines();
		
		for (int i = 0; i < possibleLines.getLines().size(); i++)
		{
			for (int j = 1; j < possibleLines.getLines().size(); j++)
			{
				String first = possibleLines.getLine(j).get(1);
				String second = possibleLines.getLine(j-1).get(1);
				
				if (first.toLowerCase().compareTo(second.toLowerCase()) < 0)
				{
					ArrayList<String> tempLine = possibleLines.getLine(j-1);
					possibleLines.setLine(possibleLines.getLine(j), j-1);
					possibleLines.setLine(tempLine, j);
				}
			}
		}
		
		if (stopWords != null)
		{
			possibleLines.removeStopWords(stopWords);
		}
		
		sortedLines = possibleLines;
	}
	
	public Lines getSortedLines()
	{
		return sortedLines;
	}
}
