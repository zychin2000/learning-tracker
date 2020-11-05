package sjsu.cs157a.model;


/**
 * This is a model class represents a note entity
 * 
 * @author bellawei
 *
 */
public class Note {
	private int principle_id;
	private int note_id;
	private String title;
	private String content;
	private String text_font;
	private String file_type;
	private String image_type;
	private String size;
	
	
	// constructor 
	public Note(int principle_id, int note_id, String title, String content) {
		super();
		this.principle_id = principle_id;
		this.note_id = note_id;
		this.title = title;
		this.content = content;
	}

	public String getText_font() {
		return text_font;
	}

	public void setText_font(String text_font) {
		this.text_font = text_font;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getImage_type() {
		return image_type;
	}

	public void setImage_type(String image_type) {
		this.image_type = image_type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	private String link;

	public int getPrinciple_id() {
		return principle_id;
	}

	public void setPrinciple_id(int principle_id) {
		this.principle_id = principle_id;
	}

	public int getNote_id() {
		return note_id;
	}

	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
