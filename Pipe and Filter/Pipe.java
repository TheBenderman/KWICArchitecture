// Pipe.java
//
// Written by : Nathan Bender
//
// Purpose:
// A pipe is a connector that passes the data from one filter to the next. My pipe includes a buffered array of strings, which is
// used as the output for one filter to the input of another. The first filter writes to the pipe using the write function,
// then the next filter reads the written data from the pipe.
//

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
	
	// write to the pipe
	public void write(ArrayList<String> writtenLines)
	{
		lines = writtenLines;
	}

	// read from the pipe
	public ArrayList<String> read()
	{
		return lines;
	}
}
