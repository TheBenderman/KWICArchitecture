import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class FileWriter {

	private Lines lines;
	
	public FileWriter(Lines tempLines)
	{
		lines = tempLines;
	}
	
	public void writeLinesToFile()
	{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"))) 
		{		
			for(ArrayList<String> line : lines.getLines())
			{
				writer.write("key: " + line.get(0) + "\n");
				
				String valueString = "";
				for (int i = 1; i < line.size(); i++)
				{
					valueString = valueString + line.get(i) + " ";
				}
				writer.write("value: " + valueString + "\n\n");
			}
		}
		catch (FileNotFoundException fNF)
		{
			
		}
		catch (Exception ex)
		{
			
		}
	}
}
