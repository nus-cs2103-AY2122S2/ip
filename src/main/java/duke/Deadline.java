package duke;
public class Deadline extends Task {

    protected String by;

    /**
     * 
     * @param description the description of the task
     * @param by the time the task is to be completed by
     * @param isDone a boolean that shows if the task is done
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * 
     * @param description the description of the task
     * @param by the time the task is to be completed by
     */
    public Deadline(String description, String by) {
        super(description, false);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * @return string in the format it is saved as
     */

    public String parseString() {
        return "D |" + super.parseString() + "| " + by; 
    }
}
