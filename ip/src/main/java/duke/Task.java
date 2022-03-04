package duke;

public class Task {
    /* describes the task being done*/
    protected String description;
    /* boolean to tell if task is done*/
    protected boolean isDone;

    /**
     * Constructor to create task object
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * deals with writing file
     * @return String of description
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * retrieves status of task
     * @return status of task X for done and nothing for not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * set task as not done
     * @return false boolean which means undone
     */
    public void setAsUnmarked(){
        this.isDone = false;
    }

    /**
     * set task as done
     * @return true boolean which means done
     */
    public void setAsMarked(){
        this.isDone = true;
    }

    /**
     * Returns the string representation of the duke.Task object.
     *
     * @return the string listing the elements in duke.TaskList
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }



}