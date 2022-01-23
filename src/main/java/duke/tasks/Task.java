package duke.tasks;
import java.io.Serializable;

public abstract class Task implements Serializable {

    protected String item;
    protected boolean completed;

    public Task (String obj) {
        item = obj;
        completed = false;
    }

    public void setCompleted(boolean value) {
        completed = value;
    }

    public String toString() {
        String display = completed ? "[X]" : "[ ]";
        return display + " " + item.toString();
    }


}