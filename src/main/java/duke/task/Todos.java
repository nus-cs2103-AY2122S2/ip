package duke.task;

/**
 * Represents a Todo task created by the user that are/will be stored in the database.
 * A <code>Todo</code> inherits from <code>Tasks</code> and is represented by its
 * name, and completion status e.g.,<code>"Eat dinner", true</code>
 */
public class Todos extends Tasks {
    //Printing Format Constants
    public static final String DATAFORMAT_TODO = "T |";
    public static final String DATA_COMPLETEDTASK = " X | " ;
    public static final String DATA_INCOMPLETEDTASK = "   | " ;

    //Database Format Constants
    public static final String PRINTFORMAT_TODO = "[T]";
    public static final String PRINT_COMPLETEDTASK = "[X] " ;
    public static final String PRINT_INCOMPLETEDTASK = "[ ] " ;

    // Constructor of Todos
    public Todos(String taskName) {
        super(taskName);
    }

    public Todos(String taskName, boolean completion) {
        super(taskName, completion);
    }

    /**
     * Returns a new completed instance of the task.
     *
     * @return a new instance of the task that has been completed.
     */
    @Override
    public Todos completeTask() {
        return new Todos(super.getName(), true);
    }

    /**
     * Returns a new uncompleted instance of the task.
     *
     * @return a new instance of the task that has not been completed.
     */
    @Override
    public Todos uncompleteTask() {
        return new Todos(super.getName(), false);
    }

    /**
     *
     * @return
     */
    public String toDatabaseString() {
        return DATAFORMAT_TODO + (this.getCompletion() ?
                DATA_COMPLETEDTASK : DATA_INCOMPLETEDTASK) + super.getName();
    }

    /**
     *
     * @return
     */
    public String toString() {
        return PRINTFORMAT_TODO + (super.getCompletion() ?
                PRINT_COMPLETEDTASK : PRINT_INCOMPLETEDTASK) + super.getName() ;
    }
}
