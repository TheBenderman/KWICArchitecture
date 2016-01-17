import java.util.ArrayList;

public class Lines {
	private ArrayList<ArrayList<String>> lines;
	
	public Lines()
	{
		lines = new ArrayList<ArrayList<String>>();
	}
	
	public Lines(ArrayList<ArrayList<String>> copyLines)
	{
		lines = copyLines;
	}
	
	public ArrayList<ArrayList<String>> getLines()
	{
		return lines;
	}
	
	public void addLine(ArrayList<String> line)
	{
		lines.add(line);
	}
	
	public void setLine(ArrayList<String> line, int index)
	{
		lines.set(index, line);
	}
	
	public ArrayList<String> getLine(int index)
	{
		return lines.get(index);
	}
	
	public void removeStopWords(ArrayList<String> stopWords)
	{
		ArrayList<ArrayList<String>> tempLines = new ArrayList<ArrayList<String>>();
		
		for (ArrayList<String> line : lines)
		{
			String value = line.get(1);
			if (!stopWords.contains(value.toLowerCase()))
				tempLines.add(line);
		}
		
		lines = tempLines;
	}
}
