package core.task;

import utilities.OutputFormatter;

public class Deadline extends Task {
    protected String by;

    private Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline getInstance(String description, String by) {
        return new Deadline(description, by);
    }

    @Override
    public String toString() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        outputFormatter.appendAll("[D]", super.toString(), " (by: ", this.by, ")");
        return outputFormatter.getFormattedOutput();
    }
}
