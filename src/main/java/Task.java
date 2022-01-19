public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getStatus() {
        return done ? "[X] " + description  : "[ ] " + description;
    }

    public void setDone() {
        this.done = true;
    }

    public void setunDone() {
        this.done = false;
    }
}
