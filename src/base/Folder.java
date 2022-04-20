package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Folder implements Comparable<Folder>, Serializable{

	private ArrayList<Note> notes;
	private String name;
	private static final long serialVersionUID = 1L;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	};
	
	public void addNote(Note n) {
		notes.add(n);
	};
	
	public String getName() {
		return name;
	};
	
	public ArrayList<Note> getNotes(){
		return notes;
	};
	
	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for (Note n : notes) {
			if(n instanceof TextNote)
				nText++;
			if(n instanceof ImageNote)
				nImage++;
		};
		return name + ":" + nText + ":" + nImage;
	};
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	};
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Note))
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	};
	
	@Override
	public int compareTo(Folder o) {
		if(this.name.compareTo(o.name) > 0) {
			return 1;
		}else if(this.name.compareTo(o.name) < 0) {
			return -1;
		}else {
			return 0;
		}
	}
	
	public void sortNotes() {
		List<Note> notes = new ArrayList<Note>();
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords){
		
		List<Note> noteslist = new ArrayList<Note>();
		String search[] = keywords.toLowerCase().split(" ");
		
		for (Note n : notes) {
			String title = n.getTitle().toLowerCase();
			String arg = null;
			boolean contains = false;
			
			if (n instanceof TextNote) {
				String content = ((TextNote) n).getContent().toLowerCase();
				arg = title + " " + content;
			}else {
				arg = title;
			}
			
			
			for (int i = 0; i < search.length ; i++) {
				if (search[i].equals("or")) {
					i++; 
				} else if (arg.contains(search[i])) {
					contains = true;
				} else if ( i+1 < search.length && search[i+1].equals("or")) {
					i++;
				} else {
					contains = false;
				}
			}
			
			if (contains) {
				noteslist.add(n);
			}
		}
		return noteslist;
		
	}
	
	public boolean removeNotes(String title) {
		for(Note note: notes) {
			if(note.getTitle().equals(title)) {
				notes.remove(note);
				return true;
			}
		}
		return false;
	}
}
