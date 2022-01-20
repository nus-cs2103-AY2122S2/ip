package core.task;

import utilities.OutputFormatter;

public class Event extends Task {
    protected String at;

    private Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static Event getInstance(String description, String at) {
        return new Event(description, at);
    }

    @Override
    public String toString() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        outputFormatter.appendAll("[E]", super.toString(), " (at: ", this.at, ")");
        return outputFormatter.getFormattedOutput();
    }
}
