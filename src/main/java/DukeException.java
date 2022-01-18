public class DukeException extends Exception {
    String message = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    @Override
    public String toString() {
        return message;
    }

}
