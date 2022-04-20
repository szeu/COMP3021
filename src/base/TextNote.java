package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note implements java.io.Serializable{
	private String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	public String getContent() {
		return content;
	}
	
	public String getTextFromFile(String absolutePath){
		String result = "";
		FileInputStream fis = null;
		InputStreamReader in = null;
		BufferedReader rd = null;
		try {
			fis = new FileInputStream(absolutePath);
			in = new InputStreamReader(fis);
			rd = new BufferedReader(in);
			String line = "";
			while((line = rd.readLine()) != null)
				result = result + line;
			rd.close();
		}
		catch(FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch(IOException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		if(pathFolder == "") {
			pathFolder = ".";
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			File file = new File(pathFolder + File.separator + File.separator + getTitle().replaceAll(" ", "_") + ".txt");
			file.createNewFile();
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			fw.close();
		}
		catch(FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch(IOException e2) {
			e2.printStackTrace();
		}
	}
	
	public void updateContent(String newContent) {
		this.content = newContent;
	}
}
