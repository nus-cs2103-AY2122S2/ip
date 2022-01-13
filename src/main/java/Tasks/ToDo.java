package Tasks;

// Tasks.ToDo is a Tasks.Task
public class ToDo extends Task {
    public ToDo(String task, boolean markStatus) {
        super(task, markStatus);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
