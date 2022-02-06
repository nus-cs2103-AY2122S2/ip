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
        int days;
        try {
            days = Integer.parseInt(args);
        } catch (NumberFormatException ex) {
            throw new DukeIllegalArgumentException("Days must be a positive number");
        }

        if (days < 0) {
            throw new DukeIllegalArgumentException("Days must be a positive number");
        }

        final LocalDateTime endTime = LocalDateTime.now().plus(days, ChronoUnit.DAYS);
        linePrinter.print(String.format("Here are your tasks in %d days:", days));
        taskList.doForEach((index, task) -> {
            assert task != null;
            task.getDate().ifPresent(date -> {
                if (date.isBefore(endTime)) {
                    linePrinter.print(task.getReadableString());
                }
            });
        });
        return true;
    }
}
