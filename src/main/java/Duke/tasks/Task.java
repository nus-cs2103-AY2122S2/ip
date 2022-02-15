package Duke.tasks;

import java.util.Date;

public class Task {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    String finished() {
        if(this.isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public String getDescription() {
        return this.description;
    }

    public Date getDate() {
        return new Date(3000,12,30);
    }
    @Override
    public String toString() {
        return this.description;
    }
}
