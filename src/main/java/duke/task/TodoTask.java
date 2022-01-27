package duke.task;

/**
 * Tasks set as todo
 */
public class TodoTask extends Task{

    /**
     * Constructor for todo task.
     *
     * @param title Title of task
     */
    public TodoTask(String title) {
        super(title);
        this.type = TaskType.TODO;
    }

    /**
     * Constructor for todo task with specified done state.
     * @param title
     * @param isDone
     */
    public TodoTask(String title, Boolean isDone) {
        super(title, isDone);
        this.type = TaskType.TODO;
    }
}
