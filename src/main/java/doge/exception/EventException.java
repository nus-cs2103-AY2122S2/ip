package doge.exception;

public class EventException extends DogeException {
    public EventException(String message) {
        super(message + "\n\t\tCan you follow the format when using the 'event' command?\n" + "<FORMAT> deadline " +
                "[task" +
                " name] / " +
                "[yyyy-MM-dd]" +
                " [HH:mm]\n" + "<EXAMPLE> event attend lesson on how to follow simple instructions / 2022-01-29 " +
                "12:00");
    }
}
