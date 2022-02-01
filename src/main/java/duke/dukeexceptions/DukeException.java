package duke.dukeexceptions;

public class DukeException extends Exception {

    public static final String DIVIDER = "_________________________________________________";

    DukeException(String msg) {
        super(msg);
    }

    /**
     * Prints a error msg
     */
    public String callback() {
        return "";
    }
}
