package duke.tasks;

public class Todo extends Task {

    public Todo(int rank, String command) {
        super(rank, command);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}