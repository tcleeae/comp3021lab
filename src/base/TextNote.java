package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note{

	private String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public String getContent()
	{
		return this.content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		File file = new File(absolutePath);
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void exportTextToFile(String pathFolder) {
        try {
        	if (pathFolder == "") {
        		pathFolder = ".";
        	}
        	File file = new File( pathFolder + File.separator + getTitle().replace(" ", "_") + ".txt");
        	if (!file.exists()) {
       	     file.createNewFile();
       	  	}
        	FileWriter fw = new FileWriter(file);
      	  	BufferedWriter bw = new BufferedWriter(fw);
      	  	bw.write(content);
      	  	bw.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}

}
