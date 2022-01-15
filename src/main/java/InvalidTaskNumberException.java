public class InvalidTaskNumberException extends DukeException{
    InvalidTaskNumberException(String taskType) {
        super("Please enter a valid task number to " + taskType);
    }
}
