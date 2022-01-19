public class DeadlineException extends Exception {

    public DeadlineException(String message) {
        super("☹ OOPS!!! " + message);
    }
}

