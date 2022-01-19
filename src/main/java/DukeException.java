public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        super(message);
    }

    public String getMessage(String message) {
        return "OOPS! ";
    }
}
