package duke;

public class InvalidTaskException extends Exception {
    public InvalidTaskException() {
        super("Invalid task number!");
    }
}
