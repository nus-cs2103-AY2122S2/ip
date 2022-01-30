package duke.Command;

import duke.DukeExceptions.DukeException;
import duke.Storage;
import duke.Ui;
import duke.TaskList;

import java.io.IOException;

public class ByeCommand extends Command {
    public ByeCommand(String input) {
        super(input);
    }

    /**
     * Execeute the exit command
     * @param taskList currentTaskList
     * @param ui ui class that helps to print suitable command
     * @param storage storage that manage saving and loading data
     * @throws DukeException an error message
     * @throws IOException error saving to the file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        ui.bye();
        storage.saveFile(taskList);
    }

    /**
     * Checks if this command is a goodbye command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
