package baron.tasks;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString(String delimiter) {
        return "T" + delimiter + super.toSaveString(delimiter);
    }
}
