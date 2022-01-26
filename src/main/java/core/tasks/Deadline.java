package core.tasks;

import core.exceptions.NoDeadlineMentionedException;
import core.exceptions.NoDescriptionGivenException;
import utilities.OutputFormatter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate byDate;
    protected LocalTime byTime;
    protected String by;

    private Deadline(String description, String by) {
        super(description);
        String[] dateAndTime = by.split(" ");
        this.byDate = LocalDate.parse(dateAndTime[0]);
        if (dateAndTime.length > 1) {
            this.byTime = LocalTime.parse(dateAndTime[1]);
        }
        this.by = by;
    }

    @Override
    public String getLabel() {
        return "D";
    }

    @Override
    public String getPeripheralInfo() {
        return this.by;
    }

    public static Deadline getInstance(String description, String by) throws NoDeadlineMentionedException, NoDescriptionGivenException, DateTimeParseException {
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
        outputFormatter.appendAll("[D]", super.toString(), " (by: ", byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        if (byTime != null) {
            outputFormatter.appendAll(" ", byTime.format(DateTimeFormatter.ofPattern("Ka")), ")");
            return outputFormatter.getFormattedOutput();
        }
        outputFormatter.append(")");
        return outputFormatter.getFormattedOutput();
    }
}
