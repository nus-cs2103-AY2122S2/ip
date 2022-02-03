package Duke.Tasks;

/**
 * The TaskCreator class creates Task objects
 * from parameters passed into it.
 *
 * @author  Melvin Chan Zijun
 */
public class TaskCreator {
    /**
     * Prefix of Task.
     */
    private final char prefix;

    /**
     * Whether the task is complete. Set to false by default
     */
    private final boolean isCompleted;

    /**
     * Name of Task.
     */
    private final String name;

    /**
     * Date of Task.
     */
    private final String date;

    /**
     * Time of Task.
     */
    private final String time;

    /**
     * Sole constructor.
     *
     * @param prefix - prefix of Task
     * @param isCompleted - task completion state
     * @param name - name of task
     * @param date - date of task
     * @param time - time of task
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
     * This method creates a new Task with the class variables as
     * the parameters for the Task to be created. If isCompleted
     * is true, the newly created Task will be marked before being
     * returned.
     *
     * @return Task - the type of Task returned depends on the
     *                prefix stored in this TaskCreator
     */
    public Task createTask() {
        Task task;
        if (prefix == 'D') {
            task = new TaskDeadline(this.name, this.date, this.time);
        } else if (prefix == 'E') {
            task = new TaskEvent(this.name, this.date, this.time);
        } else if (prefix == 'T') {
            task = new TaskToDo(this.name);
        } else {
            System.out.println("Help!\n"
                    + "Weird looking task found in Duke's memory!\n"
                    + "Please go to Duke's memory to check!\n"
                    + "(Found a task that is not a Deadline, Event or ToDo\n"
                    + "in data/duke.txt)\n");
            task = new TaskEmpty();
        }

        if (this.isCompleted && !task.isEmptyTask()) {
            task.mark();
        }
        return  task;
    }
}
