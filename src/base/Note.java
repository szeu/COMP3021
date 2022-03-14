package base;

import java.util.Objects;
import java.util.Date;

public class Note implements Comparable<Note> , java.io.Serializable{
	private Date date;
	private String title;
	
	public Note(String title) {
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle() {
		return title;
	}

	public boolean equals(Note note) {
		return this.title == note.title;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Note))
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}

	@Override
	public int compareTo(Note o) {
		// TODO Auto-generated method stub
		return date.compareTo(o.date);
	}

	@Override
	public String toString() {
		return date.toString() + "\t" + title;
	}
	
	
}
