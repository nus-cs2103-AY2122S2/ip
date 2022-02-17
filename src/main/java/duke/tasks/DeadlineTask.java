package duke.tasks;

import duke.commands.Commands;
import duke.exceptions.InvalidParameterException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDate deadline;

    private DeadlineTask(String taskName, LocalDate deadline) {
        super(taskName, "D");
        this.deadline = deadline;
    }

    public static DeadlineTask DeadlineTaskBuilder(String taskName) throws InvalidParameterException {
        if (taskName.length() == Commands.deadline.toString().length()) {
            throw new InvalidParameterException("A Deadline Task must have a task name.\nPlease try again!");
        }
        int lastIndexOfBackslash = taskName.lastIndexOf('/');
        if (lastIndexOfBackslash == -1) {
            throw new InvalidParameterException("A Deadline Task must have an associated deadline.\nPlease try again!");
        }

        return new DeadlineTask(
                taskName.substring(Commands.deadline.toString().length() + 1, lastIndexOfBackslash).trim(),
                LocalDate.parse(
                        taskName.substring(lastIndexOfBackslash + 4),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), deadline.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
    }

}
