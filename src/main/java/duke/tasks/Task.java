package duke.tasks;

import java.io.Serializable;

public abstract class Task implements Serializable {

    protected String description;
    protected boolean completed;

    public Task (String obj) {
        description = obj;
        completed = false;
    }

    protected void setCompleted(boolean value) {
        completed = value;
    }

    public String toString() {
        String display = completed ? "[X]" : "[ ]";
        return display + " " + description.toString();
    }


}