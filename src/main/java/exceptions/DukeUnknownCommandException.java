package exceptions;

public class DukeUnknownCommandException extends Exception {
    public DukeUnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
