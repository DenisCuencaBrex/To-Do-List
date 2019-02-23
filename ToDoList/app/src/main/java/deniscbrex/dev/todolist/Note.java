package deniscbrex.dev.todolist;

/**
 * @author Denis Cuenca Brex
 */

public class Note {
    private String m_Title;
    private String m_Description;

    private boolean m_Idea;
    private boolean m_Todo;
    private boolean m_Important;

    //Getter of String m_title
    public String getTitle() {
        return m_Title;
    }
    //Setter of String m_title
    public void setTitle(String m_Title) {
        this.m_Title = m_Title;
    }


    //Getter and Setter of String m_Description
    public String getDescription() {
        return m_Description;
    }

    public void setDescription(String m_Description) {
        this.m_Description = m_Description;
    }



    //Getter and Setter of bollean m_Idea
    public boolean isIdea() {
        return m_Idea;
    }

    public void setIdea(boolean m_Idea) {
        this.m_Idea = m_Idea;
    }



    //Getter and Setter of m_Todo
    public boolean isTodo() {
        return m_Todo;
    }

    public void setTodo(boolean m_Todo) {
        this.m_Todo = m_Todo;
    }



    //Getter and Setter of m_Important
    public boolean isImportant() {
        return m_Important;
    }

    public void setImportant(boolean m_Important) {
        this.m_Important = m_Important;
    }
}
