public class Task {

    private String description;
    private boolean completed;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getCompleted() {
        return this.completed;
    }
}
