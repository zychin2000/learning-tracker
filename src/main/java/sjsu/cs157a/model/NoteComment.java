package sjsu.cs157a.model;

import sjsu.cs157a.models.User;

import java.io.Serializable;

public class NoteComment implements Serializable {

    int comment_id;
    String title;
    String content;
    Note note;
    User user;

    public NoteComment(int comment_id, String title, String content, Note note, User user) {
        this.comment_id = comment_id;
        this.title = title;
        this.content = content;
        this.note = note;
        this.user = user;
    }

    public NoteComment(String title, String content, Note note, User user) {
        this.title = title;
        this.content = content;
        this.note = note;
        this.user = user;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
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

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
