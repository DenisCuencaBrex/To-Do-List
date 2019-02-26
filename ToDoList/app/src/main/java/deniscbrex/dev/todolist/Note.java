package deniscbrex.dev.todolist;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Denis Cuenca Brex
 */

public class Note {
    private String m_Title;
    private String m_Description;

    private boolean m_Idea;
    private boolean m_Todo;
    private boolean m_Important;

    private static final String JSON_TITLE = "title";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_IDEA = "idea";
    private static final String JSON_TODO = "todo";
    private static final String JSON_IMPORTANT = "important";


    //constructor base vacio
    public Note(){

    }
    //constructor para crear una nota de un fichero JSON
    public Note(JSONObject jo) throws JSONException{
        m_Title = jo.getString(JSON_TITLE);
        m_Description = jo.getString(JSON_DESCRIPTION);
        m_Idea = jo.getBoolean(JSON_IDEA);
        m_Todo = jo.getBoolean(JSON_TODO);
        m_Important = jo.getBoolean(JSON_IMPORTANT);
    }

    //constructor para serializar el fichero JSON
    public JSONObject convertNoteToJSON() throws JSONException{
        JSONObject jo = new JSONObject();

        jo.put(JSON_TITLE, m_Title);
        jo.put(JSON_DESCRIPTION, m_Description);
        jo.put(JSON_IDEA, m_Idea);
        jo.put(JSON_TODO, m_Todo);
        jo.put(JSON_IMPORTANT, m_Important);

        return jo;
    }


    public String getTitle() {
        return m_Title;
    }

    public void setTitle(String m_Title) {
        this.m_Title = m_Title;
    }



    public String getDescription() {
        return m_Description;
    }

    public void setDescription(String m_Description) {
        this.m_Description = m_Description;
    }



    public boolean isIdea() {
        return m_Idea;
    }

    public void setIdea(boolean m_Idea) {
        this.m_Idea = m_Idea;
    }



    public boolean isTodo() {
        return m_Todo;
    }

    public void setTodo(boolean m_Todo) {
        this.m_Todo = m_Todo;
    }



    public boolean isImportant() {
        return m_Important;
    }

    public void setImportant(boolean m_Important) {
        this.m_Important = m_Important;
    }
}
