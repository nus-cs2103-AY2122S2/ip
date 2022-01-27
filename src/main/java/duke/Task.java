package duke;

public abstract class Task {

    protected final String name;
    protected boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Check if the name of the task contains the keyword.
     * @param keyword The keyword.
     * @return True if the name contains the keyword else false.
     */
    public boolean contains(String keyword) {
        return name.contains(keyword);
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    abstract public String toStore();

}
