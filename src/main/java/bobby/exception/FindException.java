package bobby.exception;

public class FindException extends BobbyException {
    private String errType;

    public FindException(String message) {
        super(message);
        errType = message;
    }

    @Override
    public String toString() {
        String errorString;
        switch(errType) {
        case "empty_command": errorString = "\tWhat is the keyword?";
            break;
        case "empty_task": errorString = "\tYou have completely no tasks!";
            break;
        default: errorString = "\tCannot find any task matching the keyword.";
        }
        return errorString;
    }
}
