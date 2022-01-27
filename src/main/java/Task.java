public class Task {
    protected String content;
    protected boolean done;

    public Task(String content) {
        this.content = content;
        this.done = false;
    }

    public Task(String content, boolean done) {
        this.content = content;
        this.done = done;
    }

    public void mark() {
        this.done = true;
    }
    public void unmark() {
        this.done = false;
    }
    @Override
    public String toString() {
        return "[T]" + (this.done? "[X] " : "[ ] ") + this.content;
    }

    public String toFileString() {
        return "T, " + (this.done ? "1, " : "0, ") + this.content;
    }

}
