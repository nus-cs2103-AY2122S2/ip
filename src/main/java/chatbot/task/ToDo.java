package chatbot.task;


/**
 * Represents a Task with no Timestamp
 */
public class ToDo extends Task {

    /**
     * Instantiates a new ToDo.
     *
     * @param title The title of the task.
     */
    public ToDo(String title) {
        super(title, "T", null);
    }

    /**
     * Instantiates a new ToDo.
     *
     * @param title The title of the task.
     * @param done  The completion status of the task.
     */
    public ToDo(String title, String done) {
        super(title, "T", done, null);
    }
}
