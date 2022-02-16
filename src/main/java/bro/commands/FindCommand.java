package bro.commands;

import java.util.ArrayList;
import java.util.stream.Collectors;

import bro.Storage;
import bro.TaskManager;
import bro.Ui;
import bro.tasks.Task;

/**
 * Represents a command to find all tasks containing the specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes and finds all tasks with names containing the supplied keyword.
     *
     * @param storage Not used.
     * @param ui The Ui to display search results to.
     * @param taskManager The Task Manager containing all tasks to search in.
     * @return True after printing search results.
     */
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) {

        assert !keyword.equals("") : "No keyword supplied!";

        ArrayList<Task> results = new ArrayList<>(
                new ArrayList<>(taskManager.getTaskList())
                        .stream()
                        .filter(x -> x.getTaskName().contains(keyword))
                        .collect(Collectors.toList()));

        this.response = ui.showFindResults(new TaskManager(results));
        return true;
    }
}
