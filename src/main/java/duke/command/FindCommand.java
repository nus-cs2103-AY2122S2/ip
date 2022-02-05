package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.dukeexceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class FindCommand extends Command {


    public FindCommand(String input) {
        super(input);
    }

    /**
     * Find the keyword from the user's input and any matched task will be listed
     *
     * @param taskList currentTaskList
     * @param ui       ui class that helps to print suitable command
     * @param storage  storage that manage saving and loading data
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String keywords = input.substring(5);
        ArrayList<String> searchedTaskList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(keywords)) {
                searchedTaskList.add((i + 1) + "." + taskList.get(i).toString());
            }
        }
        ui.showFoundTask(searchedTaskList);

    }
}
