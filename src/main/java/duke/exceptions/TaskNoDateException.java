package duke.exceptions;

public class TaskNoDateException extends RuntimeException {
    public TaskNoDateException(String msg) {
        super(msg);
    }

    public TaskNoDateException() {
        super();
    }
}
