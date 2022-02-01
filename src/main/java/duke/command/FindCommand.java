package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;
import duke.task.Task;

/**
 * Represents a command that finds and prints all the tasks
 * in the TaskList that matches the search term upon execution.
 */
public class FindCommand extends Command {
    private String searchTerm;

    /**
     * A constructor that stores the searchTerm.
     *
     * @param searchTerm The String to search through the TaskList.
     */
    public FindCommand(String searchTerm) {
        super();
        this.searchTerm = searchTerm;
    }

    /**
     * Executes the command by finding all Tasks that have descriptions that contain the search term
     * then lists them all out.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param storage A Storage object to handle saving of data.
     * @return A String which is Duke's response.
     * @throws DukeException If there is an issue saving the tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        int numOfTasks = taskList.numOfTasks();
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (int i = 0; i < numOfTasks; i++) {
            String taskFullDescription = taskList.getTask(i).getTaskName();
            if (taskFullDescription.contains(searchTerm)) {
                matchedTasks.add(taskList.getTask(i));
            }
        }
        String response = "Here are the matching tasks in your list:" + "\n";
        for (int i = 0; i < matchedTasks.size(); i++) {
            response += i + 1 + "." + matchedTasks.get(i).toString() + "\n";
        }
        return response;
    }

    /**
     * Returns true if it is an exit command and false otherwise.
     *
     * @return a boolean.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
