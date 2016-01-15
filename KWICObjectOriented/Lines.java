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
}
