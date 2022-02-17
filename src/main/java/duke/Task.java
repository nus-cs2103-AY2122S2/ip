package duke;

/**
 * This is an abstract Task class that creates Task instances with a title,
 * whether task is completed and the type of Task.
 *
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */
public abstract class Task implements Comparable<Task> {
    protected String name;
    protected int done;
    protected Character type;

    public Task(String n, int d) {
        name = n;
        done = d;
    }

    /**
     * Returns the description name of the task instance
     * @return name of task
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the particular type of task: Todo as 'T', Deadline as 'D', Event as 'E'
     * @return 'T', 'D' or 'E'
     */
    public Character getType() {

        return type;
    }

    /**
     * Setting a Task to be done
     * @param done is an integer that represents a task being completed (1) or not (0)
     */
    public void setDone(int done) {
        this.done = done;
    }

    @Override
    /**
     * Allows lexicographical comparison of Task title
     */
    public int compareTo(Task task) {
        return name.toLowerCase().compareTo(task.name.toLowerCase());
    }

    /**
     * Sets the Task's type of: Todo (T), Deadline (D), Event (E)
     */
    public String getTaskIcon() {
        return String.format("%c", type);
    }
}
