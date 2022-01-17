package duke.Tasks;

// duke.Tasks.ToDo is a duke.Tasks.Task
public class ToDo extends Task {
    public ToDo(String task, boolean markStatus) {
        super(task, markStatus);
    }

    @Override
    public String getStringCmd() {
        // mark status | type | descriptor
        return super.getMarkStatus() + "&T&" + super.getTask();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
