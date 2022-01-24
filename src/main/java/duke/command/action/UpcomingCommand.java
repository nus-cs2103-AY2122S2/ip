package duke.command.action;

import duke.command.Command;
import duke.task.TaskList;
import duke.exception.DukeIllegalArgumentException;
import duke.util.IPrintable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UpcomingCommand extends Command {
    public UpcomingCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(IPrintable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        int days = -1;
        try {
            days = Integer.parseInt(args);
        } catch (NumberFormatException ex) {
            throw new DukeIllegalArgumentException("Days must be a positive number");
        }

        if (days < 0) {
            throw new DukeIllegalArgumentException("Days must be a positive number");
        }

        LocalDateTime endTime = LocalDateTime.now().plus(days, ChronoUnit.DAYS);
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
