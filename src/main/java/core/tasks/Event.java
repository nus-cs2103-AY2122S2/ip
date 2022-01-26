package core.tasks;

import core.exceptions.NoDescriptionGivenException;
import core.exceptions.NoEventLocaleMentionedException;
import utilities.OutputFormatter;

public class Event extends Task {
    protected String at;

    private Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static Event getInstance(String description, String at) throws NoEventLocaleMentionedException, NoDescriptionGivenException {
        if (description.isBlank() || description.isBlank()) {
            throw new NoDescriptionGivenException();
        }

        if (at.isEmpty() || at.isBlank()) {
            throw new NoEventLocaleMentionedException();
        }
        return new Event(description, at);
    }

    @Override
    public String getPeripheralInfo() {
        return this.at;
    }

    @Override
    public String getLabel() {
        return "E";
    }

    @Override
    public String toString() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        outputFormatter.appendAll("[E]", super.toString(), " (at: ", this.at, ")");
        return outputFormatter.getFormattedOutput();
    }
}
