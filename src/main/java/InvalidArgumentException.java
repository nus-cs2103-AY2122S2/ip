public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException() {
        super("", false, true);
    }

    public InvalidArgumentException(String errorMsg) {
        super(errorMsg, false, true);
    }

    public InvalidArgumentException(String errorMsg, boolean isHidden) {
        super(errorMsg, isHidden, true);
    }


}
