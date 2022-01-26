package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.task.TaskList;
import duke.util.IPrintable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Represents a handler for the schedule command.
 */
public class ScheduleCommand extends Command {
    /**
     * Creates a handler for the schedule command.
     * @param args Arguments supplied to the command handler.
     */
    ScheduleCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(IPrintable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        LocalDateTime dayStart = parseDate(this.args);
        LocalDateTime dayEnd = dayStart.plus(1, ChronoUnit.DAYS);
        linePrinter.print(String.format("Here are your tasks on %s:", this.args));
        taskList.forEach((idx, task) -> {
            task.getDate().ifPresent(date -> {
                if (date.isBefore(dayEnd) && date.isAfter(dayStart)) {
                    linePrinter.print(task.getReadableString());
                }
            });
        });
        return true;
    }
}
