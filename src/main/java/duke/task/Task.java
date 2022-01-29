package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String info) {
        description = info;
        isDone = false;
    }

    /**
     * Gets the duke.task description and status. Returns the formatted status and description of the duke.task.
     *
     * @return specified task.Task description
     */
    public String getTask() {
        String icon = getStatusIcon();
        String output = "[" + icon + "] " + description;
        return output;
    }

    /**
     * Gets the duke.task description.
     *
     * @return specified task.Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the duke.task status. Returns the formatted duke.task status icon.
     *
     * @return specified task.Task status icon i.e. [X] or  [ ]
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Sets the duke.task status. Changes duke.task status to true.
     */
    public void setDone(){
        isDone = true;
    }

    /**
     * Sets the duke.task status. Changes duke.task status to false.
     */
    public void setNotDone(){
        isDone = false;
    }

}