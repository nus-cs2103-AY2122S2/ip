package spark.parser.params;

public class AddTodoParams extends AddTaskParams {
    /**
     * Creates an object containing the necessary information
     * for the creation of a new todo.
     *
     * @param title the title of the todo.
     */
    public AddTodoParams(String title) {
        super(title);
    }
}
