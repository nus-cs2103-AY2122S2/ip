package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.task.TaskList;
import duke.util.Printable;

import java.time.LocalDateTime;

public class BetweenCommand extends Command {
    BetweenCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(Printable linePrinter, TaskList taskList) throws DukeIllegalArgumentException {
        final String[] argParts = args.split(" and ");
        if (argParts.length < 2) {
            throw new DukeIllegalArgumentException("Not in the format <date> and <date>");
        }
        final LocalDateTime start = parseDateTime(argParts[0]);
        final LocalDateTime end = parseDateTime(argParts[1]);

        linePrinter.print(String.format("Here are your tasks in between %s and %s:",
                argParts[0], argParts[1]));
        taskList.doForEach((index, task) -> {
            task.getDate().ifPresent(date -> {
                if (date.isBefore(end) && date.isAfter(start)) {
                    linePrinter.print(task.getReadableString());
                }
            });
        });
        return true;
    }
}
