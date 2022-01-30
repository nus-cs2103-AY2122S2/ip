package duke.Command;

import duke.DukeExceptions.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;
import java.util.ArrayList;

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
        ArrayList<String> searched_taskList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(keywords)) {
                searched_taskList.add((i + 1) + "." + taskList.get(i).toString());
            }
        }
        ui.showFoundTask(searched_taskList);

    }
}
