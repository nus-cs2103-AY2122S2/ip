package duke.command;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Printable;

/**
 * Represents a handler for the schedule command.
 */
public class ScheduleCommand extends Command {
    /**
     * Creates a handler for the schedule command.
     *
     * @param args Arguments supplied to the command handler.
     */
    ScheduleCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(Printable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        final LocalDateTime dayStart = parseDate(this.args);
        assert dayStart != null;
        final LocalDateTime dayEnd = dayStart.plusDays(1);

        List<Task> tasksOnDay = taskList.filter(task -> task.getDate()
                .map(date -> date.isBefore(dayEnd) && date.isAfter(dayStart))
                .orElse(false));

        if (tasksOnDay.size() == 0) {
            linePrinter.print(String.format("You have no tasks on %s!", this.args));
        } else {
            linePrinter.print(String.format("Here are your tasks on %s:", this.args));
            tasksOnDay.sort(Comparator.comparing(x -> x.getDate().orElse(LocalDateTime.MAX)));
            tasksOnDay.forEach(task -> {
                linePrinter.print(task.getReadableString());
            });
        }
        return true;
    }
}
