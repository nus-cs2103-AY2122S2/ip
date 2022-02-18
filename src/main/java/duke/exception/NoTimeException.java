package duke.exception;

public class NoTimeException extends DukeException {
    private String typeOfCommand;

    public NoTimeException(String err, String typeOfCommand) {
        super(err);
        this.typeOfCommand = typeOfCommand;
    }

    @Override
    public String getMessage() {
        return " The time of " + this.typeOfCommand + " must not be empty!!";
    }
}