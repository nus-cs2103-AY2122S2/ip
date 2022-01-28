package bernie.commands;

import bernie.enums.Type;
import bernie.exceptions.InvalidArgumentException;
import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.TaskList;
import bernie.ui.UiHandler;

public class FindCommand extends Command {
    String[] parsedArr;

    public FindCommand(TaskList tasks, UiHandler uiHandler, Storage storage, Parser parser, String input)
            throws InvalidArgumentException {
        super(tasks, uiHandler, storage, parser, input);
        this.parsedArr = parser.getParams(Type.FIND, input);
    }

    public void execute() {
        String description = parsedArr[0];
        uiHandler.showFoundTasksMsg(tasks, description);
    }
}
