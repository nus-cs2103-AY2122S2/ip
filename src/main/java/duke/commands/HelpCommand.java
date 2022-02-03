package duke.commands;

import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Add a todo to the tasklist.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n\t\t"
            + "Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        System.out.println(
                "\t" + AddTodoCommand.MESSAGE_USAGE
                + "\n\t" + AddEventCommand.MESSAGE_USAGE
                + "\n\t" + AddDeadlineCommand.MESSAGE_USAGE
                + "\n\t" + DeleteCommand.MESSAGE_USAGE
                + "\n\t" + ListCommand.MESSAGE_USAGE
                + "\n\t" + HelpCommand.MESSAGE_USAGE
                + "\n\t" + ExitCommand.MESSAGE_USAGE);
    }
}