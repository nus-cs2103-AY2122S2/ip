package duke.command;

import duke.dukeexceptions.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Execeute the command according to the user's input
     *
     * @param taskList currentTaskList
     * @param ui ui class that helps to print suitable command
     * @param storage storage that manage saving and loading data
     * @throws DukeException an error message
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showAllTasks(taskList);
    }
}
