public class Deadline extends Task {

    public Duke.TaskType type = Duke.TaskType.DEADLINE;
    public String taskName;
    public String deadline;

    public Deadline(String taskName, String deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.done ? "X" : " ", this.taskName, this.deadline);
    }

    public String exportToString() {
        return String.format("%s %s %s %s", this.type, this.taskName, this.done, this.deadline);
    }
}