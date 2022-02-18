package duke.commands;

import duke.data.TasksEditor;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Add a todo to the tasklist.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n\t"
            + "Example: " + COMMAND_WORD;

    @Override
    public String execute(TasksEditor tasksEditor, Ui ui, Storage storage) {
        String msg = AddTodoCommand.MESSAGE_USAGE
                + "\n" + AddEventCommand.MESSAGE_USAGE
                + "\n" + AddDeadlineCommand.MESSAGE_USAGE
                + "\n" + DeleteCommand.MESSAGE_USAGE
                + "\n" + ListCommand.MESSAGE_USAGE
                + "\n" + HelpCommand.MESSAGE_USAGE
                + "\n" + ExitCommand.MESSAGE_USAGE + "\n";
        return msg;
    }
}