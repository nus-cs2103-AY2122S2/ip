package duke;
public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setMark() {
        this.isDone = true;
    }

    public void setUnmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public char getType() {
        if (this instanceof Event) {
            return 'e';
        } else if (this instanceof Deadline) {
            return 'd';
        } else if (this instanceof Todo) {
            return 't';
        } else {
            return 'x';
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
