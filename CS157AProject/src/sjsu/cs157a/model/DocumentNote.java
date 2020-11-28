package sjsu.cs157a.model;

import org.json.*;


public class DocumentNote extends Note {

    JSONObject documentContent;

    public DocumentNote(String title, String content) {
        super(title, content);
    }

    public DocumentNote(int note_id, String title, String content, String documentContent) throws JSONException {
        super(note_id, title, content);


        if (documentContent != null)
            this.documentContent = new JSONObject(documentContent);
    }


    public JSONObject getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(JSONObject documentContent) {
        this.documentContent = documentContent;
    }
}
