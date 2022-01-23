package Tasks;

public class Task {
    private boolean completed;
    private String type;
    private String description;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    // Overloaded constructor
    public Task(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public void toggleCompleted() {
        this.completed = true;
    }

    public void toggleUncompleted() {
        this.completed = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    @Override
    public String toString() {

        if (this.completed) {
            return "[" + type + "]" + "[X] " + description;
        } else {
            return "[" + type + "]" + "[ ] " + description;
        }
    }

    public String dBText() {
        String complete = this.getCompleted() ? "1" : "0";
        return String.format("T|%s|%s", complete, this.getDescription());
    }
}
