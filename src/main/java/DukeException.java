public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super("____________________________________________________________\n" +
                "☹ OOPS!!!" + errorMessage +
                "\n____________________________________________________________");
    }
}