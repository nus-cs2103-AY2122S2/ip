package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.task.TaskList;
import duke.util.IPrintable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Represents a handler for the upcoming command.
 */
public class UpcomingCommand extends Command {
    /**
     * Creates a handler for the upcoming command.
     * @param args Arguments supplied to the command handler.
     */
    UpcomingCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(IPrintable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        int days;
        try {
            days = Integer.parseInt(args);
        } catch (NumberFormatException ex) {
            throw new DukeIllegalArgumentException("Days must be a positive number");
        }

        if (days < 0) {
            throw new DukeIllegalArgumentException("Days must be a positive number");
        }

        LocalDateTime endTime = LocalDateTime.now().plus(days, ChronoUnit.DAYS);
        linePrinter.print(String.format("Here are your tasks in %d days:", days));
        taskList.forEach((idx, task) -> {
            task.getDate().ifPresent(date -> {
                if (date.isBefore(endTime)) {
                    linePrinter.print(task.getReadableString());
                }
            });
        });
        return true;
    }
}
