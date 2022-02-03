package duke.task;

public class ToDo extends Task{
    public ToDo(String name) {
        super("T", name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
