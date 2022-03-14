package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public NoteBook(String file) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook) in.readObject();
			folders = n.folders;
			in.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	//Overloading method createTextNote
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	public boolean insertNote(String folderName, Note note) {
		boolean existFolder = false;
		for(Folder f : folders) {
			if(f.getName() == folderName) {
				existFolder = true;
				boolean existNote = false;
				for(Note n : f.getNotes()) {
					if(n.getTitle() == note.getTitle()) {
						existNote = true;
						System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
						return false;
					}
				}
				if(!existNote) {
					f.addNote(note);
					return true;
				}
			}
		}
		if(!existFolder) {
			Folder folder = new Folder(folderName);
			folder.addNote(note);
			folders.add(folder);
			return true;
		}
		return false;
	}
	
	public void sortFolders() {
		for(Folder f : folders) {
			f.sortNotes();
		}
		Collections.sort(folders);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> list = new ArrayList<Note>();
		for(Folder f: folders) {
			list.addAll(f.searchNotes(keywords));
		}
		return list;
	}
	
	public boolean save(String file) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
