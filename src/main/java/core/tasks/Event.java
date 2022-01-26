package core.tasks;

import core.exceptions.NoDescriptionGivenException;
import core.exceptions.NoEventLocaleMentionedException;
import utilities.OutputFormatter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate atDate;
    protected LocalTime atTime;

    private Event(String description, String at) {
        super(description);
        String[] dateAndTime = at.split(" ");
        this.atDate = LocalDate.parse(dateAndTime[0]);
        if (dateAndTime.length > 1) {
            this.atTime = LocalTime.parse(dateAndTime[1]);
        }
    }

    public static Event getInstance(String description, String at) throws DateTimeParseException, NoEventLocaleMentionedException, NoDescriptionGivenException {
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
        outputFormatter.appendAll("[E]", super.toString(), " (at: ", atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        if (atTime != null) {
            outputFormatter.appendAll(" ", atTime.format(DateTimeFormatter.ofPattern("Ka")), ")");
            return outputFormatter.getFormattedOutput();
        }
        outputFormatter.append(")");
        return outputFormatter.getFormattedOutput();
    }
}
