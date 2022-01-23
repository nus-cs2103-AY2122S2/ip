package echo.command;

import echo.storage.Storage;
import echo.task.TaskList;
import echo.ui.Ui;

public class FindCommand extends Command {

    public static final String COMMAND = "find";

    private final String DESCRIPTION;

    public FindCommand(String description) {
        this.DESCRIPTION = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String find = tasks.find(DESCRIPTION);
        if (find.length() == 0) {
            ui.showCantFind();
        } else {
            ui.showFind(find);
        }
    }
}
