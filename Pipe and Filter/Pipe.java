import java.util.ArrayList;

public class Pipe {
	
	private ArrayList<String> lines;
	
	public Pipe()
	{
		lines = new ArrayList<String>();
	}
	
	public Pipe(ArrayList<String> info)
	{
		lines = info;
	}
	
	public void write(ArrayList<String> writtenLines)
	{
		lines = writtenLines;
	}

	
	public ArrayList<String> read()
	{
		return lines;
	}
}
