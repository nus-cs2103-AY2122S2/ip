public abstract class Task {
    private String description;
    private boolean marked;

    public Task(String description) {
        this.description = description;
        this.marked = false;
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
