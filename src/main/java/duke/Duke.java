package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the Duke task manager bot.
 *
 * @author William Ming
 * @version 0.1
 */
public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private boolean isExit;

    public Duke(String dirPath, String fileName) {
        ui = new duke.ui.Ui();
        storage = new Storage(dirPath, fileName);
        taskList = new TaskList();
        isExit = false;
    }

    public String loadDataAndWelcome() {
        return ui.showWelcome() + storage.loadData(taskList, ui);
    }

    public String getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput, taskList.getLength());
            isExit = command.isExit();
            return command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.showCommandError(e.getMessage());
        }
    }

    public boolean isExit() {
        return isExit;
    }
}
