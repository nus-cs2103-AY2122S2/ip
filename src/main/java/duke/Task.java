package duke;

/**
 * This class specifies all the methods and variables of a task.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public abstract class Task {
    // Class variables
    protected boolean isDone;
    protected String desc;

    /**
     * Assigns desc and done to this instance.
     *
     * @param desc the task description.
     * @param isDone the current completion status of the task.
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Changes the status of the Task to done.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Changes the completion status of the task to not done.
     */
    public void setAsNotDone() {
        this.isDone = false;
    }

    /**
     * Changes the string format of this event object.
     */
    public abstract String changeFormat();

    public String getDesc() {
        return desc;
    }

    public boolean getIsDone() {
        return isDone;
    }
}
