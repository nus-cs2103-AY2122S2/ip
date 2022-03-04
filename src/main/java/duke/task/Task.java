package duke.task;
public abstract class Task {
    private boolean isDone;
    private String taskDescription;
    private String tag;

    /**
     * Constructor for Task
     *
     * @param taskDescription
     * @param tag
     */
    public Task (String taskDescription, String tag) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        this.tag = tag;
    }

    /**
     * Checks if a task is done
     *
     * @return
     */
    public boolean checkIsDone() {
        return this.isDone;
    }

    /**
     * Gets task description
     *
     * @return
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getTag() {
        return this.tag;
    }

    public abstract String toSaveDataFormat();

    @Override
    public String toString() {
        String isDoneRepresentation;
        if (this.checkIsDone() == true) {
            isDoneRepresentation = "[Done!] ";
        } else {
            isDoneRepresentation = "[] ";
        }
        return "[" + this.getTag() + "]" + isDoneRepresentation + this.getTaskDescription();

    }

}
