public class DukeException extends Throwable {
    protected String type;

    public DukeException(String type) {
        this.type = type;
    }

    public DukeException() {
        this.type = "";
    }

    public String toString() {
        if (type.equals("")) {
            return " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        return String.format(" ☹ OOPS!!! The description of a %s cannot be empty.", type);
    }
}
