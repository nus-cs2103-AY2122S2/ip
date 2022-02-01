package duke.task;

public class Deadline extends TaskWithDateTime {
    protected String by;

    public Deadline(String description, String by) {
        super(description, by);
        this.by = by;
    }

    @Override
    public String toString() {
        String s = "[D]" + super.toString();
        return s.replace(" (at: ", " (by: ");
    }

    @Override
    public String writeToFile() {
        return "D | " + super.writeToFile();
    }
}
