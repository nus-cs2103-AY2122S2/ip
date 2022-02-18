package duke;

import java.time.LocalDateTime;

public class Task {

    public boolean isDone;
    public String desc;
    public String type;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
        this.type = " ";
    }

    public Task(String desc, String type) {
        this.desc = desc;
        this.isDone = false;
        this.type = type;
    }

    public Task(String desc, boolean done, String type) {
        this.desc = desc;
        this.isDone = done;
        this.type = type;
    }

    public String getDone() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        return this.type;
    }

    public Task mark() {
        return new Task(this.desc, true, this.type);
    }

    public Task unmark() {
        return new Task(this.desc);
    }

    public LocalDateTime getBy() {
        return null;
    }
}
