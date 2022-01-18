public class Deadline extends Task {
    /**
     * The deadline
     */
    private String deadlineTime;

    /**
     * Constructor for Deadline objects
     *
     * @param deadlineName  the deadline name
     */
    public Deadline(String deadlineName, String deadlineTime) {
        super(deadlineName);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns a String representation of the Deadline
     *
     * @return  Deadline in String
     */
    @Override
    public String toString() {
        String box1 = "[D]";
        String doneness;
        if (super.getDone()) {
            doneness = "[X] ";
        } else {
            doneness = "[ ] ";
        }
        String deadlineName = super.toString();
        String time = " (by: " + deadlineTime + ")";
        return box1 + doneness + deadlineName + time;
    }
}