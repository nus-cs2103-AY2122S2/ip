package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.task.TaskList;
import duke.util.Printable;

import java.time.LocalDateTime;

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
    public boolean execute(Printable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        final LocalDateTime dayStart = parseDate(this.args);
        final LocalDateTime dayEnd = dayStart.plusDays(1);
        linePrinter.print(String.format("Here are your tasks on %s:", this.args));
        taskList.doForEach((index, task) -> {
            task.getDate().ifPresent(date -> {
                if (date.isBefore(dayEnd) && date.isAfter(dayStart)) {
                    linePrinter.print(task.getReadableString());
                }
            });
        });
        return true;
    }
}
