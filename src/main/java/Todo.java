/**
 * A class that creates Todo objects
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
