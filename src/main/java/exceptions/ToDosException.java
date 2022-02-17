package exceptions;

/**
 * A class that belongs to the Exceptions Package.
 * This class encapsulates the message that should be displayed when users parsed an invalid input
 * for the {@link tasks.ToDos} class.
 */
public class ToDosException extends TaskException {

    /**
     * Constructs TodosException
     */
    public ToDosException() {
        super();
    }

    /**
     * Creates message to be displayed when a TodosException happens.
     * @return Message as a string.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "The description of a todo cannot be empty.";
    }
}
