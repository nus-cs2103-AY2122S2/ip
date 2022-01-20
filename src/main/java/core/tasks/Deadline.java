package core.tasks;

import core.exceptions.NoDeadlineMentionedException;
import core.exceptions.NoDescriptionGivenException;
import utilities.OutputFormatter;

public class Deadline extends Task {
    protected String by;

    private Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline getInstance(String description, String by) throws NoDeadlineMentionedException, NoDescriptionGivenException {
        if (description.isBlank() || description.isBlank()) {
            throw new NoDescriptionGivenException();
        }

        if (by.isBlank() || by.isEmpty()) {
            throw new NoDeadlineMentionedException();
        }
        return new Deadline(description, by);
    }

    @Override
    public String toString() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        outputFormatter.appendAll("[D]", super.toString(), " (by: ", this.by, ")");
        return outputFormatter.getFormattedOutput();
    }
}
