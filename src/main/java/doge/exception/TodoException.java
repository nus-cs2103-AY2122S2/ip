package doge.exception;

public class TodoException extends DogeException {
    /**
     * Constructor for TodoException class.
     * @param message the exception message
     */
    public TodoException(String message) {
        super(message + "\n\t\tCan you follow the format when using the 'todo' command?\n"
                + "<FORMAT> todo [task " + "name]\n"
                + "<EXAMPLE> todo read and follow simple instructions");
    }
}
