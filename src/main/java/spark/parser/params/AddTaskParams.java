package spark.parser.params;

public abstract class AddTaskParams {
    protected String title;

    /**
     * Creates an object containing the necessary information
     * for the creation of a new task
     *
     * @param title the title of the task
     */
    public AddTaskParams(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
