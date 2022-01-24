package duke.command.action;

import duke.command.Command;
import duke.task.TaskList;
import duke.exception.DukeIllegalArgumentException;
import duke.util.IPrintable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ScheduleCommand extends Command {
    public ScheduleCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(IPrintable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        LocalDateTime dayStart = parseDate(this.args);
        LocalDateTime dayEnd = dayStart.plus(1, ChronoUnit.DAYS);
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
