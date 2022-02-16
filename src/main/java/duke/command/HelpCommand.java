package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Help command for Duke.
 */
public class HelpCommand extends Command {
    public static final String HELP_COMMAND = "help";

    public HelpCommand() {
        super(HELP_COMMAND);
    }

    @Override
    public String execute(String input, TaskList taskList, Storage storage) throws DukeException {
        String helpStatement = "";

        helpStatement += ListCommand.LIST_COMMAND + ": List all the tasks you have.\n";
        helpStatement += FindCommand.FIND_COMMAND + ": Find tasks based on keywords.\n";
        helpStatement += EditTaskMarkCommand.MARK_COMMAND + ": Mark tasks as done.\n";
        helpStatement += EditTaskMarkCommand.UNMARK_COMMAND + ": Unmark tasks.\n";
        helpStatement += TodoCommand.TODO_COMMAND + ": Add todo tasks.\n";
        helpStatement += DeadlineCommand.DEADLINE_COMMAND + ": Add deadline tasks.\n";
        helpStatement += EventCommand.EVENT_COMMAND + ": Add event tasks.\n";
        helpStatement += DeleteCommand.DELETE_COMMAND + ": Delete tasks.\n";
        helpStatement += HELP_COMMAND + ": Get more help.\n";
        helpStatement += ByeCommand.BYE_COMMAND + ": Quit the application.\n";

        return helpStatement;
    }
}
