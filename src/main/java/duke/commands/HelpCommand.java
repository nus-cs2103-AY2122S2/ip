package duke.commands;

import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Add a todo to the tasklist.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        System.out.println(
                AddTodoCommand.MESSAGE_USAGE
                + "\n" + AddEventCommand.MESSAGE_USAGE
                + "\n" + AddDeadlineCommand.MESSAGE_USAGE
                + "\n" + DeleteCommand.MESSAGE_USAGE
                + "\n" + ListCommand.MESSAGE_USAGE
                + "\n" + HelpCommand.MESSAGE_USAGE
                + "\n" + ExitCommand.MESSAGE_USAGE);
    }
}