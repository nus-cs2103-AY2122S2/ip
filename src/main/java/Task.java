public class Task {
    protected String desc;
    protected boolean isDone = false;

    public Task(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return getStatus() + " " + desc;
    }

}
