package bob.exception;

public class PriorityException extends BobException {
    /**
     * Constructor for a Priority exception.
     */
    public PriorityException() {
        super(new StringBuilder("Invalid priority, you need to provide the priority in the following format:\n")
                .append("\tpriority [task ID] [priority level]\n")
                .append("\te.g. priority 1 HIGH")
                .toString());
    }
}
