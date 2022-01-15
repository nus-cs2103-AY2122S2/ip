public class InvalidInputException extends AlfredException {
    static String ERROR_MESSAGE = "Sorry sir, that's a valid command but invalid input. Likely due to wrong format after '(un)mark', 'delete', 'todo', 'deadline' or 'event'.";
    InvalidInputException() {
        super(InvalidInputException.ERROR_MESSAGE);
    }
}
