import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReader {

	public FileReader()
	{
		
	}
	
	public ArrayList<ArrayList<String>> getLines(String fileName)
	{
		ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
		
		try
		{
			List<String> linesList = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			
			for (String line : linesList)
			{
				if(line != null && !line.isEmpty())
					lines.add(new ArrayList<String>(Arrays.asList(line.split(" "))));
			}
		}
		catch(FileNotFoundException fNF)
		{
			System.out.println("Error! File: " + fileName + " not found!");
			return null;
		}
		catch(IOException io)
		{
			System.out.println("Error reading file: " + fileName + "!");
			return null;
		}
		
		return lines;
	}
	
	public ArrayList<String> getStopWords(String fileName)
	{
		ArrayList<String> stopWords = new ArrayList<String>();
		
		try
		{
			List<String> linesList = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			
			for (String line : linesList)
			{
				if(line != null && !line.isEmpty())
					stopWords.add(line.toLowerCase());
			}
		}
		catch(FileNotFoundException fNF)
		{
			System.out.println("Error! File: " + fileName + " not found!");
			return null;
		}
		catch(IOException io)
		{
			System.out.println("Error reading file: " + fileName + "!");
			return null;
		}
		
		return stopWords;
	}
}
