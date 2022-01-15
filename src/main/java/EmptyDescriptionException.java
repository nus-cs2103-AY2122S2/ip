public class EmptyDescriptionException extends DukeException {
    EmptyDescriptionException(String taskType) {
        super("OOPS!!! The description of " + taskType + " cannot be empty");
    }
}
