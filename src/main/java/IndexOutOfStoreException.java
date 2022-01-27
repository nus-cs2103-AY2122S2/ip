public class IndexOutOfStoreException extends RonException {
    public static final String message = "Task index not found.";

    @Override
    public String toString() {
        return super.toString() + message;
    }
}
