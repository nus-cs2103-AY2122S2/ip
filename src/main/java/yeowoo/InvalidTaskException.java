package yeowoo;

public class InvalidTaskException extends Exception {
    public InvalidTaskException() {
        super("Invalid task number!");
    }
}
