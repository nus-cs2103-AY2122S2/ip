package tasks;

/**
 * <h1>Todo</h1>
 * <p>
 * Todo class handles the implementation of tasks that do not have
 * time associated with them.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
public class Todo extends Task {

    // Symbol for Todo class.
    private static final String SYMBOL = "[T]";

    // stores the start index value.
    private static final int START_INDEX = 0;

    // used to differentiate between constructors
    private static final int DUMMY_VARIABLE = 1;

    /**
     * Constructor of Todo class.
     * @param message the text given by user.
     * returns an instance of Todo.
     */
    public Todo (String message) {
        super(message);
    }

    /**
     * Constructor for Todoclass.
     * @param str the string output of Todo.
     * @param dummyVariable int to differentiate from other constructor.
     */
    public Todo(String str, int dummyVariable) {
        super(str.substring(SYMBOL.length()).trim(), DUMMY_VARIABLE);
    }

    /**
     * checks if the string is an todo task.
     * @param str the string representation of the task.
     * @return true if  the task is todo.
     */
    public static boolean isTodo(String str) {
        return str.substring(START_INDEX, SYMBOL.length()).contains(SYMBOL);
    }



    /**
     * returns the string representation of the Todo object.
     * @return the string representation of the instance.
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString();
    }
}
