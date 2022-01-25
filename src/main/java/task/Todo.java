package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the save format in String of this task.Task object
     *
     * @return A String for the save format of this task.Task object
     */
    @Override
    public String getSaveFormat() {
        return "T," + ((isDone ? "1" : "0")) + "," + super.getSaveFormat();
    }
}
