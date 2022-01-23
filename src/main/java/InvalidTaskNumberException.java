public class InvalidTaskNumberException extends SaitamaException {
    InvalidTaskNumberException(String taskType) {
        super("Please enter a valid task number to " + taskType + "!");
    }
}
