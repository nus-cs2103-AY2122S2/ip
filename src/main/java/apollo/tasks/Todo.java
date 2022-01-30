package apollo.tasks;

/**
 * Class for {@code Todo} tasks.
 * Extends {@code Task} superclass.
 */
public class Todo extends Task{

    /**
     * Constructor for {@code Todo}.
     *
     * @param description Of task.
     */
    public Todo(String description) {
        super(description, Type.TODO);
    }
}
