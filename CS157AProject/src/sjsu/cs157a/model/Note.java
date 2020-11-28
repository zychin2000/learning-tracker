package sjsu.cs157a.model;

import java.io.InputStream;
import java.io.Serializable;

/**
 * This is a model class represents a note entity
 * 
 * @author bellawei
 *
 */
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;

	private int note_id;    // auto-increment in database 
	private int class_id;   
	private String title;
	private String content;
	private String text_font;
	private String file_type;
	private String image_type;
	private String size;
	private String link;
	private String docContent;  // content in note_doc table 
	private InputStream inputStream; // for storing pic in mysql

	/**
	 * Constructor for a note in note_meta table
	 * 
	 * @param title
	 * @param content
	 */
	public Note(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	
	/**
	 * Constructor for displaying a note in Note page from note_meta table
	 * @param note_id
	 * @param title
	 * @param content
	 */
	public Note(int note_id, String title, String content) {
		super();
		this.note_id = note_id;
		this.title = title;
		this.content = content;
	}


	/**
	 * Constructor for a note in note_docu table(subclass of note_meta)
	 */
	public Note(int class_id, String title, String content, String text_font, String file_type, String docContent) {
		super();
		this.class_id = class_id;
		this.title = title;
		this.content = content;
		this.text_font = text_font;
		this.file_type = file_type;
		this.docContent = docContent; 
	}

	/**
	 * Constructor for a note in note_picture table(subclass of note_meta)
	 */
	
	
	public Note(int class_id, String title, String content, String image_type, String size, InputStream inputStream) {
		super();
		this.class_id = class_id;
		this.title = title;
		this.content = content;
		this.image_type = image_type;
		this.size = size;
		this.inputStream = inputStream; 
	}

	
	
	public int getClass_id() {
		return class_id;
	}


	public void setClass_id(int class_id) {
		this.class_id = class_id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	

	public String getDocContent() {
		return docContent;
	}

	public void setDocContent(String docContent) {
		this.docContent = docContent;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	

}
