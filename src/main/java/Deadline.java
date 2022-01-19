public class Deadline extends Task {

    public String deadline;
    public Duke.TaskType type = Duke.TaskType.DEADLINE;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.done ? "X" : " ", this.taskName, this.deadline);
    }
}