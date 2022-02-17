package duke.tasks;

import duke.commands.Commands;
import duke.exceptions.InvalidParameterException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private final LocalDate eventTime;

    private EventTask(String taskName, LocalDate eventTime) {
        super(taskName, "E");
        this.eventTime = eventTime;
    }

    public static EventTask EventTaskBuilder(String taskName) throws InvalidParameterException {
        if (taskName.length() == Commands.event.toString().length()) {
            throw new InvalidParameterException("An Event Task must have a task name.\nPlease try again!");
        }
        int lastIndexOfBackslash = taskName.lastIndexOf('/');
        if (lastIndexOfBackslash == -1) {
            throw new InvalidParameterException("An Event Task must have an associated event time.\nPlease try again!");
        }
        return new EventTask(
            taskName.substring(Commands.event.toString().length() + 1, lastIndexOfBackslash).trim(),
            LocalDate.parse(
                taskName.substring(lastIndexOfBackslash + 4),
                DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }


    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), eventTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
    }

}
