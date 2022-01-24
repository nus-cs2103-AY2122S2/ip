package duke.exceptions;

public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("Please enter the date and/or time in the specified format:\n" +
                "yyyy-MM-dd HHmm\n" +
                "yyyy-MM-dd\n" +
                "or HHmm");
    }
}
