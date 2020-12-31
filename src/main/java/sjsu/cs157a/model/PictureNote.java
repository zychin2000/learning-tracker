package sjsu.cs157a.model;

import java.io.InputStream;

/**
 * As the functionality of a picture not has already been implemented in the Note model, this class extends as a way to distinguish it's types. In the future,
 * attributes related to the note_picture table will be moved to this class.
 */
public class PictureNote extends Note{
    public PictureNote(int class_id, int note_id, String note_type, String title, String content, String image_type, String size, InputStream inputStream) {
        super(class_id, note_type, title, content, image_type, size, inputStream);
        this.setNote_id(note_id);
    }
}
