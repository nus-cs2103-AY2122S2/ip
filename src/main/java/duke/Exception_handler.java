package duke;

public class Exception_handler extends Exception {
    public Exception_handler(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return " ☹ OOPS!!!" + getMessage();
    }
}
