package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Folder implements Comparable<Folder> {
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for (Note n : notes){
			if(n instanceof TextNote) {
				nText++;
			}
			if(n instanceof ImageNote) {
				nImage++;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Folder))
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(Folder o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.name);
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords){
		String[] tokens = keywords.split(" ");
		List<Note> list = new ArrayList<Note>();
		for(String token : tokens) {
			if(token.equalsIgnoreCase("or")) {
				continue;
			}
			for(Note note : notes) {
//				System.out.println(token + '\t' + note.getTitle());
				if(note instanceof ImageNote) {
					if(note.getTitle().toLowerCase().contains(token.toLowerCase())) {
						if(!list.contains(note)) {
							list.add(note);
//							System.out.println("enter");
						}
					}
//					if(note.getTitle().regionMatches(true, 0, token, 0, token.length())) {
//						list.add(note);
//					}
				}
				if(note instanceof TextNote) {
					String content = ((TextNote)note).getContent();
					if(note.getTitle().toLowerCase().contains(token.toLowerCase()) || 
							content.toLowerCase().contains(token.toLowerCase())) {
						if(!list.contains(note)) {
							list.add(note);
//							System.out.println("enter");
						}
					}
//					if(note.getTitle().regionMatches(true, 0, token, 0, token.length()) || 
//							content.regionMatches(true, 0, token, 0, token.length())) {
//						list.add(note);
//					}
				}
			}
		}
		return list;
	}
}
