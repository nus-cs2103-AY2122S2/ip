package duke.task;

/**
 * Parent class for all tasks. Stores information regarding the task description and whether it has been completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String info) {
        description = info;
        isDone = false;
    }

    /**
     * Gets the task description and status. Returns the formatted status and description of the task.
     *
     * @return specified Task description
     */
    public String getTask() {
        String icon = getStatusIcon();
        String output = "[" + icon + "] " + description;
        return output;
    }

    /**
     * Gets the task description.
     *
     * @return specified Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the task status. Returns the formatted task status icon.
     *
     * @return specified Task status icon i.e. [X] or  [ ]
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Sets the task status. Changes task status to true.
     */
    public void setDone(){
        isDone = true;
    }

    /**
     * Sets the task status. Changes task status to false.
     */
    public void setNotDone(){
        isDone = false;
    }

}