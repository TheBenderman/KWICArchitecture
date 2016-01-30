// FileWriter.java
//
// Written by : Nathan Bender
//
// Purpose:
// This class is used to write the output of the lines to a file. The output is saved to a file called "output.txt". If the file does
// not exist, the file should be created. Also provides error checking if there are errors writing to the file.

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
	
	// Write the lines to a file
	public void writeLinesToFile()
	{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"))) 
		{		
			writer.write("Key\t\t\t\tValue\n");
			writer.write("-------------------------------------------------\n");
			
			// For each of the lines, write the key and value to the file
			for(ArrayList<String> line : lines.getLines())
			{
				writer.write(line.get(0) + "\t\t\t\t"); // write the key to the line
				
				String valueString = "";
				for (int i = 1; i < line.size(); i++) // create a string for the value
				{
					valueString = valueString + line.get(i) + " ";
				}
				writer.write(valueString + "\n\n"); // write the value to the file
			}
		}
		catch (FileNotFoundException fNF)
		{
			System.out.println("Error writing to file : output.txt!");
		}
		catch (Exception ex)
		{
			
		}
	}
}
