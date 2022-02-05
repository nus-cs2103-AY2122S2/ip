package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import task.Todo;

public class TodoCommand extends Command {
    private final String message;

    public TodoCommand(String message) {
        this.message = message;
    }
    /**
     * Add Todo Task to TaskList.
     * Also overwrite Storage.
     *
     * @param storage Storage for rewriting TaskList.
     * @param taskList TaskList that stores Tasks.
     * @throws DukeException If problems with writing to Storage.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        taskList.add(new Todo(this.message, false));
        storage.writeToFile(taskList);
        return "Got it. I've added this task: \n"
                + taskList.get(taskList.size() - 1)
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }
}
