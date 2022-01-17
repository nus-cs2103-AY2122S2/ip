package Tasks;

public class Task {
    protected String title;
    protected boolean done;

    public Task(String name) {
        this.title = name;
    }

    public String getTitle() {
        return title;
    }

    public void mark() {
        this.done = true;
    }

    public void unMark() {
        this.done = false;
    }

    public String getStatusIcon() {
        return this.done ? "[X]" : "[]";
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.getTitle());
    }
}
