package cs.project.ui.task;

/*
* This is a task object it is going to be read from assests.task.txt (really a csv)
* Every task is going to have an id a string and a title, if your email
* */

import java.util.UUID;

public class Task {
    private UUID id;
    private String title;
    private String desc;

    public Task(UUID id, String title, String desc) {
        this.id = genUUID();
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public UUID genUUID(){
        return UUID.randomUUID();
    }

}
