package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class NoteBook implements Serializable{

	private ArrayList<Folder> folders;
	
	private static final long serialVersionUID = 1L;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	};
	
	public NoteBook(String file) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook) in.readObject();
			folders= n.folders;
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	};
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	};
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	};
	
	public ArrayList<Folder> getFolders(){
		return folders;
	};
	
	public boolean insertNote(String folderName, Note note) {
		Folder f = null;
		for (Folder f1 : folders) {
			if (f1.getName() == folderName) {
				f = f1;
			};
		};
		
		if (f==null) {
			f = new Folder(folderName);
			folders.add(f);
		};

		
		for (Note n : f.getNotes()) {
			if (n.equals(note)) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			};
		};
		f.addNote(note);
				
		return true;
	};
	
	public void sortFolders() {
		for (Folder f1 : folders) {
			f1.sortNotes();
		}

		Collections.sort(folders);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> noteslist = new ArrayList<Note>();
		for (Folder f1 : folders) {
			noteslist.addAll(f1.searchNotes(keywords));
		}
		return noteslist;
	}
	
	public boolean save(String file) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public void addFolder(String folderName) {
		folders.add(new Folder(folderName));
	}
}
