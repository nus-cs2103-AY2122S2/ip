public class Deadline extends Task{
    String deadline;
    public Deadline(String description, String deadline) {
        super(description, TaskType.EVENT);
        this.deadline = deadline;
    }

    @Override
    public String getSymbol() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.deadline);
    }
}
