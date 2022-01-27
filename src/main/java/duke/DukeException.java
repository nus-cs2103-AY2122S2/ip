package duke;

public class DukeException extends Exception {
    String errorMsg;

    public DukeException(String msg) {
        this.errorMsg = msg;
    }

    String getErrorMsg() {
        return errorMsg;
    }
}
