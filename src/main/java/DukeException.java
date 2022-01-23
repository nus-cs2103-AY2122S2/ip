public class DukeException extends Exception {
    public boolean isHidden;
    public boolean isInvalidCommand;

    public DukeException(String errorMsg) {
        this(errorMsg, false);
    }

    public DukeException(String errorMsg, boolean isHidden) {
        this(errorMsg, isHidden, false);
    }

    protected DukeException(String errorMsg, boolean isHidden, boolean isInvalidCommand) {
        super(errorMsg);
        this.isHidden = isHidden;
        this.isInvalidCommand = isInvalidCommand;
    }
}
