public class InvalidCommandException extends AlfredException {
    static String ERROR_MESSAGE = "Invalid command, sir. I'm not sure what you want me to do.";

    InvalidCommandException() {
        super(InvalidCommandException.ERROR_MESSAGE);
    }
}