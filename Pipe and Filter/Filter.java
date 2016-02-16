// Filter.java
//
// Written by : Nathan Bender
//
// Purpose:
// A filter is a generic object that converts an input data stream (pipe) into an output data stream (another pipe). The filter
// uses this input data to perform its functions, then writes the data to pass it to another filter. A filter has exactly
// one input pipe and one output pipe for my purpose.
//

public abstract class Filter {
	
	protected Pipe inPipe;
	protected Pipe outPipe;
	
	public abstract void run();
	
	public void setInPipe(Pipe in)
	{
		inPipe = in;
	}
	
	public void setOutPipe(Pipe out)
	{
		outPipe = out;
	}
}
