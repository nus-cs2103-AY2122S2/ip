public class Task {
    private String name;
    private boolean done;

    public Task(String text) {
        this.name = text;
        this.done = false;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone() {
        this.done = true;
    }

    public void setUndone() {
        this.done = false;
    }

    public String toString() {
        String mark;
        if (this.done) {
            mark = "[X] ";
        } else {
            mark = "[ ] ";
        }
        return mark + this.name;
    }
}
