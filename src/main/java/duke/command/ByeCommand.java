package duke.command;

import java.io.IOException;

import duke.dukeexceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.ui.Ui;



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
     * @return a response to user input
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String result = ui.bye();
        storage.saveFile(taskList);
        return result;
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
