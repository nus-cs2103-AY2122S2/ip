
/**
 * This class represents the tasks Sana will remember
 *
 * @author  Jan Alfenson Tan
 * @version 1.0
 */
public class Task {
    /**
     * true when task is done, false otherwise
     */
    private boolean done;

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
        this.done = false;
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
    public boolean getDone() {
        return done;
    }

    /**
     * Sets the task to done or not
     *
     * @param done  sets the task's completion
     */
    public void setDone(boolean done) {
        this.done = done;
    }
}
