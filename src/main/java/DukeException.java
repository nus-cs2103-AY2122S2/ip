public class DukeException extends Exception {
    public DukeException(String err) {
        super("\n############################################################\n ☹ OOPS!!! "
                + err
                + "\n############################################################");
    }
}
