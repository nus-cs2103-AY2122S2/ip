package bobby.exception;

public class MarkException extends BobbyException {
    private String errtype;

    public MarkException(String message) {
        super(message);
        errtype = message;
    }

    @Override
    public String toString() {
        String error_string;
        switch (errtype) {
        case "empty":
            error_string = "\tWhich task?";
            break;
        case "letter":
            error_string = "\tC'mon, a letter is not a number dude";
            break;
        case "OOB":
            error_string = "\tNumber too big, count properly!";
            break;
        case "negative":
            error_string = "\tInvalid number man, cannot be 0 or negative";
            break;
        case "alr_marked":
            error_string = "\tThis task is already marked as done";
            break;
        case "alr_unmarked":
            error_string = "\tThis task is already marked as undone";
            break;
        default:
            error_string = "\tI cannot understand this..";
        }
        return error_string;
    }
}
