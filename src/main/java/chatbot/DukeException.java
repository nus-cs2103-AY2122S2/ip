package chatbot;

public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public String toString() {
        return super.getMessage();
    }
}
