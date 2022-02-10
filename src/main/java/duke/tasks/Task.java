package duke.tasks;

import java.time.LocalDate;

public class Task {

    protected String description;
    protected boolean isComplete;
    protected LocalDate taskDate;

    /**
     * Task constructor for event and deadline
     * @param description user input for description
     * @param taskDate user input for task date
     */
    public Task(String description, LocalDate taskDate) {
        this.description = description;
        this.taskDate = taskDate;
        this.isComplete = false;
    }

    /**
     * Task constructor for todo
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setComplete() {
        this.isComplete = true;
    }

    public void setIncomplete() {
        this.isComplete = false;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public LocalDate getDate() {
        return this.taskDate;
    }

    @Override
    public String toString() {
        return isComplete
                ? "[X] " + description
                : "[ ] " + description;
    }
}
