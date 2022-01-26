public class UnknownCommandException extends DukeException {
    /**
     * Returns an UnknownCommandException if an unknown command
     * is entered by the user
     *
     * @author  Ryan Aidan
     */
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}

