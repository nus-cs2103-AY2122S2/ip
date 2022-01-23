
/**
 * This class represents the tasks Sana will remember
 *
 * @author  Jan Alfenson Tan
 * @version 1.0
 */
public abstract class Task {
    /**
     * true when task is done, false otherwise
     */
    private boolean isDone;

    /**
     * name of the task
     */
    private String taskName;

    /**
     * Constructor for the task object
     *
     * @param taskName  name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Constructor for the task object
     *
     * @param taskName  the task name
     * @param isDone      whether the task is done
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }
    /**
     * Returns the task name
     *
     * @return taskName
     */
    @Override
    public String toString() {
        return taskName;
    }

    /**
     * Returns whether the task is done
     * @return  done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the task to done or not
     *
     * @param done  sets the task's completion
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Converts task into a string to be stored in mem
     *
     * @return  String representation of the task in mem
     */
    public abstract String taskToMemStr();

}
