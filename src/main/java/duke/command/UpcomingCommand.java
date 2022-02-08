package duke.command;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Task;
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

        List<Task> undoneTasksBeforeEnd = taskList.filter((task) -> {
            boolean isBeforeEnd = task.getDate()
                    .map(date -> date.isBefore(endTime))
                    .orElse(false);
            return isBeforeEnd && !task.isDone();
        });

        if (undoneTasksBeforeEnd.size() == 0) {
            linePrinter.print(String.format("You have no pending tasks in the next %d days!", days));
        } else {
            linePrinter.print(String.format("Here are your pending tasks in the next %d days:", days));
            undoneTasksBeforeEnd.sort(Comparator.comparing(x -> x.getDate().orElse(LocalDateTime.MAX)));
            undoneTasksBeforeEnd.forEach(task -> {
                linePrinter.print(task.getReadableString());
            });
        }

        return true;
    }

    /**
     * Parses the days argument and ensures that it is a non-negative integer.
     *
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
