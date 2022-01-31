package duke.dukeexceptions;

/**
 * Exception class handling delete task exception
 */
public class DeleteException extends DukeException{
    public DeleteException(String errorMessage) {
        super(errorMessage);
    }
}