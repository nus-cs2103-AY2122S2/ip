package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command{
    /**
     * Represents a command to find all tasks containing the specified keyword.
     */

    public FindCommand(String userInput){
        super(userInput);
    }

    /**
     * Executes and finds all tasks with names containing the supplied keyword.
     *
     * @param storage Not used.
     * @param ui The Ui to display search results to.
     * @param taskManager The Task Manager containing all tasks to search in.
     * @return True after printing search results.
     */
    @Override
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) {
        String keyword = userInput.replaceFirst("find","").strip();
        ArrayList<Task> results = new ArrayList<>(
                new ArrayList<>(taskManager.getTaskList())
                        .stream()
                        .filter(x -> x.getTaskName().contains(keyword))
                        .collect(Collectors.toList()));

        ui.showFindResults(new TaskManager(results));
        return true;
    }
}