package duke.tasks;

import duke.exceptions.DukeException;

/**
 * The TaskCreator class creates Task objects
 * from parameters passed into it.
 *
 * @author  Melvin Chan Zijun
 */
public class TaskCreator {
    /**
     * Prefix of task.
     */
    private final char prefix;

    /**
     * Whether the task is complete.
     */
    private final boolean isCompleted;

    /**
     * Name of task.
     */
    private final String name;

    /**
     * Date of task.
     */
    private final String date;

    /**
     * Time of task.
     */
    private final String time;

    /**
     * Sole constructor.
     *
     * @param prefix prefix of task
     * @param isCompleted task completion state
     * @param name name of task
     * @param date date of task
     * @param time time of task
     */
    public TaskCreator(char prefix, boolean isCompleted,
                       String name, String date, String time) {
        this.prefix = prefix;
        this.isCompleted = isCompleted;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    /**
     * Return a new task based on the variables of this class
     *
     * @return Task the type of Task returned depends on the
     *              prefix stored in this TaskCreator
     */
    public Task createTask() throws DukeException {
        Task task;
        if (prefix == 'D') {
            task = new DeadlineTask(this.name, this.date, this.time);
        } else if (prefix == 'E') {
            task = new EventTask(this.name, this.date, this.time);
        } else if (prefix == 'T') {
            task = new ToDoTask(this.name);
        } else {
            throw new DukeException("Data is corrupted! Task List will now be reset!");
        }
        if (this.isCompleted && !task.isEmptyTask()) {
            task.mark();
        }
        return task;
    }
}
