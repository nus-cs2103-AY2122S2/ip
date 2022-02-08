package doge.exception;

public class ListTasksDueException extends DogeException {
    public ListTasksDueException(String message) {
        super(message + "\n\t\tCan you follow the format when using the 'list' command to filter tasks?\n" +
                "<FORMAT> " +
                "list [limiter] [yyyy-MM-dd] [HH:mm]\n" + "<EXAMPLE> list < 2022-01-29 12:00");
    }
}
