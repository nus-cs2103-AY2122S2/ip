package baron.exceptions;

public class BaronException extends Exception {
    public BaronException(String message) {
        super(message);
    }

    public String toString() {
        return "☹ OOPS!!! " + getMessage();
    }
}
