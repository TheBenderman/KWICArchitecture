
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
