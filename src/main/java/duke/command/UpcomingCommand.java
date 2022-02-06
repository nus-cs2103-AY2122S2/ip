package duke.command;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import duke.exception.DukeIllegalArgumentException;
import duke.task.TaskList;
import duke.util.Printable;

/**
 * Represents a handler for the upcoming command.
 */
public class UpcomingCommand extends Command {
    /**
     * Creates a handler for the upcoming command.
     *
     * @param args Arguments supplied to the command handler.
     */
    UpcomingCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(Printable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        int days = this.parseDays(this.args);
        final LocalDateTime endTime = LocalDateTime.now().plus(days, ChronoUnit.DAYS);
        linePrinter.print(String.format("Here are your tasks in %d days:", days));

        taskList.doForEach((index, task) -> {
            task.getDate().ifPresent(date -> {
                if (date.isBefore(endTime)) {
                    linePrinter.print(task.getReadableString());
                }
            });
        });
        return true;
    }

    /**
     * Parses the days argument and ensures that it is a non-negative integer.
     * @param daysInString The days argument as a String.
     * @return The parsed days argument as an Integer.
     * @throws DukeIllegalArgumentException If the days argument is not a non-negative integer.
     */
    private int parseDays(String daysInString) throws DukeIllegalArgumentException {
        int days;
        try {
            days = Integer.parseInt(daysInString);
        } catch (NumberFormatException ex) {
            throw new DukeIllegalArgumentException("Days must be a non-negative number");
        }

        if (days < 0) {
            throw new DukeIllegalArgumentException("Days must be a non-negative number");
        }

        return days;
    }
}
