package duke.exception;

import java.util.Objects;

/**
 * Class for handling exceptions when no task type specified
 */
public class NoTaskTypeException extends DukeException {

    public String typeOfCommand;

    public NoTaskTypeException(String e, String typeOfCommand) {
        super(e);
        this.typeOfCommand = typeOfCommand;
    }

    @Override
    public String getMessage() {
        if (Objects.equals(typeOfCommand, "DELETE")) {
            return "Please tell me which task to delete ";
        } else if (Objects.equals(typeOfCommand, "DONE")) {
            return "Please tell me which task to mark as done.";
        }
        return "No task indicated";
    }
}
