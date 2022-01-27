package bernie.commands;

import bernie.enums.Type;
import bernie.exceptions.InvalidArgumentException;
import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.Task;
import bernie.tasks.TaskList;
import bernie.ui.UiHandler;

public class MarkCommand extends Command {
    String[] parsedArr;

    public MarkCommand(TaskList tasks, UiHandler uiHandler, Storage storage, Parser parser, String input)
            throws InvalidArgumentException {
        super(tasks, uiHandler, storage, parser, input);
        this.parsedArr = parser.getParams(Type.MARK, input);
    }

    /**
     * Mark or unmark a task number depending on the input. Handles error if user
     * enters invalid mark inputs etc.
     */
    public void execute() {
        String action = parsedArr[0];
        String taskNum = parsedArr[1];
        if (action.equals("mark")) {
            Task markedTask = tasks.markTask(Type.MARK, taskNum);
            uiHandler.showDoneMsg(markedTask);
        } else if (action.equals("unmark")) {
            Task unmarkedTask = tasks.markTask(Type.UNMARK, taskNum);
            uiHandler.showUndoneMsg(unmarkedTask);
        }
    }
}
