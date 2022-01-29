package duke;

public class DukeException extends Exception {
    String message;

    public DukeException(String errorMessage) {
        super(errorMessage);
        message = errorMessage;
    }

    @Override
    public String toString(){
        return message;
    }
}