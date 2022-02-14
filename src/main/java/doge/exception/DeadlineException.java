package doge.exception;

public class DeadlineException extends DogeException {
    /**
     * Constructor for DeadlineException class.
     * @param message the exception message
     */
    public DeadlineException(String message) {
        super(message + "\n\t\tCan you follow the format when using the 'deadline' command?\n" + "<FORMAT> deadline "
                + "[task name] / [yyyy-MM-dd]" + " [HH:mm]\n" + "<EXAMPLE> deadline understand how to follow simple "
                + "instructions / 2022-01-30 " + "12:00");
    }
}
