public class EmptyDescException extends Exception {

    private String message;

    EmptyDescException(String message) {
        super(message);
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}
