package duke;
public class Todo extends Task {
    char type;

    public Todo(String description) {
        super(description);
        this.type = 't';
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
