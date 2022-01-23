public class Deadline extends Task{
    String deadline;
    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.deadline);
    }

    @Override
    public String getFileSaveFormat() {
        return String.format("%s | %s", super.getFileSaveFormat(), deadline);
    }
}
