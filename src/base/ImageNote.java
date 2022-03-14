package base;

import java.io.File;

public class ImageNote extends Note implements java.io.Serializable{
	private File image;
	
	public ImageNote(String title) {
		super(title);
	}
}
