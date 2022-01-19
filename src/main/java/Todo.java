/**
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Todo Class.
 */


public class Todo extends Task {

    // Symbol for Todo class.
    private static final String SYMBOL = "[T]";

    /**
     * Constructor of Todo class.
     * @param message the text given by user.
     * returns an instance of Todo.
     */
    Todo (String message) {
        super(message);
    }

    /**
     * toString returns the string representation of the Todo object.
     * @return the string representation of the instance.
     */
    @Override
    public String toString() {
        return SYMBOL + super.toString();
    }
}
