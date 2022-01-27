public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the task description and status. Returns the formatted status and description of the task.
     *
     * @return specified Task description
     */
    public String getTask() {
        String icon = this.getStatusIcon();
        String output = "[" + icon + "] " + this.description;
        return output;
    }

    /**
     * Gets the task description.
     *
     * @return specified Task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the task status. Returns the formatted task status icon.
     *
     * @return specified Task status icon i.e. [X] or  [ ]
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the task status. Changes task status to true.
     */
    public void setDone(){
        this.isDone = true;
    }

    /**
     * Sets the task status. Changes task status to false.
     */
    public void setNotDone(){
        this.isDone = false;
    }

}