package arthur.task;

/**
 * A class that creates Task.Todo objects
 */
public class Todo extends Task {

    public Todo(String e) {
        super(e);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
