package duke.exceptions;

public class FailedTaskParseException extends DukeException {
    public FailedTaskParseException(String userInput){
        super("Failed to parse task from: " + userInput);
    }
}
