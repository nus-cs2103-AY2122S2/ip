package myTasks;

/**
 * Task is the parent class of Deadline, Event and Todo. Task contains information such as the task itself and whether
 * it has been completed.
 */
public class Task {
    public String description;
    public boolean isDone;

    /**
     * Creates a new task with the task description. Set the completion of the task to not done as default .
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Visual icon to show the user if the task has been completed
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Method to indicate that the task is completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method to indicate that the task has not been completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }
}