package luke.commands;

import java.util.Locale;

import luke.data.TaskList;
import luke.data.tasks.Task;

public class FindCommand extends ReadCommand {

    public static final CommandAction COMMAND_ACTION = CommandAction.FIND;
    private static final String NO_ITEM_MESSAGE = "The force is unable to find any task with the keyword...\n"
           + "The keyword parsed is \"%s\".";
    private static final String START_ITEM_MESSAGE = "The force found the following matching tasks:\n";
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public CommandResult execute(TaskList tasklist) {
        String emptyMsg = String.format(NO_ITEM_MESSAGE, keyword);
        tasklist.setFiltered(x -> x.getCommandString().toLowerCase().contains(keyword));
        return readTaskList(tasklist, emptyMsg, START_ITEM_MESSAGE, true);
    }
}
