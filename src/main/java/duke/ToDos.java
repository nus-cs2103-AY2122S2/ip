package duke;

/**
 * To Dos are tasks without any date/time attached to it
 */
public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
