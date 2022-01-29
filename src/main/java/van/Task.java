package van;

public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getStatus() {
        return done ? "[X] " + description : "[ ] " + description;
    }

    public String getCompletion() {
        return done ? "1" : "0";
    }

    public String saveStatus() {
        return description;
    }

    public void setDone() {
        this.done = true;
    }

    public void setunDone() {
        this.done = false;
    }
}
