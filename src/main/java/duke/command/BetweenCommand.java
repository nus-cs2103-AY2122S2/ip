package duke.command;

import java.time.LocalDateTime;

import duke.exception.DukeIllegalArgumentException;
import duke.task.TaskList;
import duke.util.Printable;

/**
 * Represents a handler for the between command.
 */
public class BetweenCommand extends Command {
    /**
     * Creates a handler for the between command.
     *
     * @param args Arguments supplied to the command handler.
     */
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

        assert start != null;
        assert end != null;

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
