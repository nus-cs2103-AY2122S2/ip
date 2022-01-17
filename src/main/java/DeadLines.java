public class DeadLines extends Task {
    String deadline;

    public DeadLines (String task, String deadline) throws DukeException {
        super(task);
        this.deadline = deadline;
        if (task.length() < 8) {
            throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.deadline + ")";
    }
}
