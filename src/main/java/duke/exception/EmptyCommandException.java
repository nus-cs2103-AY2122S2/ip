package duke.exception;

/**
 * Class for handling exceptions in empty command
 */
public class EmptyCommandException extends DukeException {

    private String typeOfCommand;

    public EmptyCommandException(String err, String typeOfCommand) {
        super(err);
        this.typeOfCommand = typeOfCommand;
    }
    @Override
    public String getMessage() {
        return  this.typeOfCommand + " must append some conditions!!";
    }
}
