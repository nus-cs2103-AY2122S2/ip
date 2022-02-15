package mcbot.task;

/**
 * ToDo class implements the Task class to capture basic task information.
 */
public class ToDo extends Task {
    
    /**
     * Constructs a ToDo task with a task name.
     * The default value isDone is set to false.
     * 
     * @param taskName
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns the icon representing the task.
     * "T" is used to represent a ToDo task.
     *
     * @return The task icon representation.
     */
    @Override
    public String getTaskIcon() {
        return "T";
    }

    /**
     * Returns a String to be stored into the data text file.
     * The format is T | 0 | taskName .
     * 0 represents incomplete task.
     * 1 represents completed task.
     *
     * @return The data representation as a String to be stored.
     */
    @Override
    public String toDataString() {
        String isDone = super.isMarked() ? "1" : "0";
        return getTaskIcon() + " | " + isDone + " | " + taskName;
    }

    /**
     * Returns a String that describes the Task.
     * String contains the task icon, status icon and task name.
     *
     * @return The string describing the task.
     */
    @Override
    public String toString() {
        return "[" + getTaskIcon() + "][" + super.getStatusIcon() + "] " + super.taskName;
    }
}
