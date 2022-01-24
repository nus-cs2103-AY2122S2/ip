public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
        setDate(null);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

