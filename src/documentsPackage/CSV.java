package documentsPackage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSV {
	
	private static FileWriter writer;
	
	public static void createCSV(String name, ArrayList<String>titles, ArrayList<String>values) {
		try
		{
		    writer = new FileWriter("C:/Users/Yana/Documents/" + name + ".csv");
		    writer.append(name);
		    writer.append(System.lineSeparator());
		    
		    for(int i = 0; i < titles.size(); i++) {
		    	writer.append(titles.get(i));
		    	if(i < titles.size() - 1) {
		    		writer.append(",");
		    	} else {
		    	    writer.append(System.lineSeparator());
		    	} 
		    }
	
		    for(int i = 0; i < values.size(); i+= titles.size()) {
		
				for(int j = 0; j < titles.size(); j++) {
					writer.append(values.get(i + j));
					if(j < titles.size() - 1) {
						writer.append(",");
					} else {
						if(i != values.size() - titles.size()) {
							writer.append(System.lineSeparator());
						}
					}
				}
			}
		
		    writer.flush();
		    writer.close();
		}
		catch(IOException e) {
		     throw new RuntimeException(e);
		} 
	}

}
