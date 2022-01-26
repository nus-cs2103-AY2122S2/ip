public class Deadline extends Task{

    private String dueDate;

    public Deadline(String description, String dueDate, boolean isDone) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String getTaskString() {
        return "DEADLINE" + "," + super.isDone + "," + super.description + "," + this.dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + super.getDescription() + " (" + this.getDueDate() + ")";
    }
}
