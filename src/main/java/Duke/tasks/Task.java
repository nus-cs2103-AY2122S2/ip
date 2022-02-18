package Duke.tasks;

import java.time.LocalDateTime;
import java.util.Date;

public class Task {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : "O"); // mark done task with X
    }

    String finished() {
        if(this.isDone) {
            return "X";
        } else {
            return "O";
        }
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getDate() {
        LocalDateTime localDateTime = LocalDateTime.MAX;
        return  localDateTime;
    }
    @Override
    public String toString() {
        return this.description;
    }
}
