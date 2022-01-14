package DukeBot;

public class Task {
    private boolean completed;
    private String description;

    public Task(String description) {
        this.description = description;
        this.completed = false;
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
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

}
