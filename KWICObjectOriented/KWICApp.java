import java.util.ArrayList;

public class KWICApp {
	
	public KWICApp()
	{
		
	}
	
	public void run (String lineFileName)
	{
		FileReader fileReader = new FileReader();
		ArrayList<ArrayList<String>> parsedLines = fileReader.getLines(lineFileName);
		
		if (parsedLines == null)
			return;
		
		Lines lines = new Lines(parsedLines);
		
		CircularShifter circularShifter = new CircularShifter(lines);
		circularShifter.createCircularShifts();
	}
	
	public void run (String lineFileName, String stopWordFileName)
	{
		
	}

	public static void main(String[] args)
	{
		if (args.length == 2)
		{
			if (args[0].equalsIgnoreCase("-f"))
			{
				KWICApp kwicapp = new KWICApp();
				kwicapp.run(args[1]);
				
				return;
			}
		}
		else if (args.length == 4)
		{
			int sIndex = -1; // index for the stopfile
			int fIndex = -1; // index for the file containing the lines
			int runningIndex = 0; // index for the current loop
			
			for (String s : args)
			{
				if (s.equalsIgnoreCase("-s"))
					sIndex = runningIndex + 1; // if the parameter is -f, we assume the file name is the next arg
				else if (s.equalsIgnoreCase("-f"))
					fIndex = runningIndex + 1; // if the parameter is -f, we assume the safeword file name is the next arg
				
				runningIndex = runningIndex + 1;
			}
			
			if (!(sIndex == -1 || fIndex == -1))
			{
				KWICApp kwicapp = new KWICApp();
				kwicapp.run(args[fIndex], args[sIndex]);
				
				return;
			}
	
		}
		
		System.out.println("Invalid arguments.");
		System.out.println("Please call the program by using:");
		System.out.println("\t java KWICApp -f myfile.text (-s stopwords.txt)");
	}
}
