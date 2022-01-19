public class DukeException extends Exception {
    String errorMsg;
    DukeException(String msg) {
        this.errorMsg = msg;
    }

    String getErrorMsg() {
        return errorMsg;
    }
}
