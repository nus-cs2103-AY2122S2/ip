public class EmptyDescriptionException extends SaitamaException {
    EmptyDescriptionException(String taskType) {
        super("OOPS!!! The description of " + taskType + " cannot be empty.");
    }
}
