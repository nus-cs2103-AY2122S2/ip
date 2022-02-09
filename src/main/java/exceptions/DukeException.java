package exceptions;

public class DukeException extends Exception {

    String errMsg;

    public DukeException(String s) {
        super(s);
        this.errMsg = s;
    }

    public String toString() {
        return errMsg;
    }

}
