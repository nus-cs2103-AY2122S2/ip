public class LoadException extends RonException {
    public static final String message = "No current tasks found, creating new task list.";

    @Override
    public String toString() {
        return super.toString() + message;
    }
}
