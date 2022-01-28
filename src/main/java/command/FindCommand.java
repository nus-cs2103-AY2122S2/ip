package command;

import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents a type of Command - Find.
 * Supports the "Find" Command feature.
 */
public class FindCommand extends Command {

    protected String words;

    /**
     * @param words Words to search for in the TaskList
     */
    public FindCommand(String words) {
        this.words = words;
    }

    /**
     * Allows users to search for a specific task inside TaskList.
     *
     * @param tasks TaskList which stores the list of tasks
     * @param ui Ui to display necessary responses
     * @param storage Storage to perform caching features
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.words.equals("")) {
            throw new DukeException("You are searching for nothing! Please try again.");
        } else {
            ArrayList<Task> foundTasks = tasks.find(this.words);
            ui.showFoundTasksMessage(new TaskList(foundTasks));
        }
    }

}
