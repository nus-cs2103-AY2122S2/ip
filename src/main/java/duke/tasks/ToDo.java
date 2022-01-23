package duke.tasks;

/**
 * ToDo is a Task.
 * This is a simple Task that only has a description.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     * This is a Task with only a description
     *
     * @param task     the task description
     * @param isDone   the mark status of this deadline
     */
    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }


    /**
     * Get the Command (in String form) to add this ToDo to the TaskList.
     * Useful for saving/reading from the save file.
     *
     * @return a String formatted specially for a ToDo Task.
     */
    public String getStringCmd() {
        // mark status | type | descriptor
        return super.getIsDone() + "&T&" + super.getTask();
    }

    /**
     * Gets the String version of a ToDo
     *
     * @return String-formatted ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
