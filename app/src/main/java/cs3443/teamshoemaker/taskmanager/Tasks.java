package cs3443.teamshoemaker.taskmanager;
public class Tasks {
    private String title;
    private String description;

    public Tasks(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    // ... Other methods and properties ...
}
