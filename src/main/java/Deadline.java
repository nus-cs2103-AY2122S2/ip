public class Deadline extends Task{
    protected String deadLine;

    public Deadline(String description, String deadLine) {
        super(description);
        this.deadLine = deadLine;
    }

    @Override
    public String formatSave() {
        return "D |" + (super.isDone ? "1| " : "0| ") + super.description + " /by " + deadLine;
    }

    /*
     * Customized toString method for Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadLine + ")";
    }
}
