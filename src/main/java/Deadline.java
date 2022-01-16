public class Deadline extends Task{
    String deadLine;
    public Deadline(String description, String deadLine) {
        super(description);
        this.deadLine = deadLine;
    }

    /*
     * Customized toString method for Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadLine + ")";
    }
}
