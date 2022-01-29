public class Task {

    private final String description;
    private boolean completed;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean completed) {
        this.description = description.trim();
        this.completed = completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    private String getCompleted() {
        return completed ? "X" : " ";
    }

    public String toFile() {
        return this.completed + "\t" + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getCompleted() + "] " + this.description;
    }
}
