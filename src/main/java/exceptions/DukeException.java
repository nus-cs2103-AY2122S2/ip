package exceptions;

public class DukeException extends Exception {
    String errorMessage;

    public DukeException(String str2) {
        errorMessage = str2;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
