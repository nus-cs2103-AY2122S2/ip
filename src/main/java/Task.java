public class Task {
    private String description;
    private boolean marked;

    public Task(String description, boolean marked) {
        this.description = description;
        this.marked = marked;
    }

    public Task(String description) {
        this(description, false);
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    public boolean isMarked() {
        return this.marked;
    }

    @Override
    public String toString() {
        return (this.isMarked() ? "[X] " : "[] ") + this.description;
    }
}
