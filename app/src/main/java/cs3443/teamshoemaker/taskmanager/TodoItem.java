package cs3443.teamshoemaker.taskmanager;


public class TodoItem {
    int id;
    final String title;
    final String description;

    public TodoItem( String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
