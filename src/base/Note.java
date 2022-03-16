
package base;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note>, Serializable{

	private Date date;
	private String title;
	private static final long serialVersionUID = 1L;
	
	public Note(String title) {
		this.title = title;
		date = new Date(System.currentTimeMillis());
	};
	
	public String getTitle() {
		return title;
	};
	
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
	};
	
	@Override
	public int compareTo(Note o) {
		if(this.date.before(o.date)) {
			return 1;
		}else if(this.date.after(o.date)) {
			return -1;
		}else {
			return 0;
		}
	}
	
	public String toString() {
		return date.toString() + "\t" + title;
	}
	
}
