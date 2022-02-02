package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command that deletes a specific task form list.
 */
public class DeleteCommand extends Command<String> {
    private TaskList list;
    private String[] echo;
    private Storage storage;

    /**
     * Constructor that initialises this command to run.
     *
     * @param list task list to be delete from
     * @param echo the input details by user to delete a task
     * @param storage store of which list to delete from
     * @throws DukeException
     */
    public DeleteCommand(TaskList list, String[] echo, Storage storage) throws DukeException {
        this.list = list;
        this.echo = echo;
        this.storage = storage;
    }

    /**
     * Runs the deletion of task.
     *
     * @throws DukeException when the task to delete cannot be found
     */
    public String execute() throws DukeException {
        String err = "Oh no! Which task do you wish to delete? Try again :)\n";
        String wrongNumber = "Oh no! This task number does not exist. Try again :)\n";
        String wrongFormat = "Oh no! Please do not spell out the number. Try again :)\n";
        String response = "";
        int targetIndex;
        int size = list.getSize();
        if (echo.length == 1) {
            throw new DukeException(err);
        }
        String taskNum = echo[1];
        if (taskNum.isEmpty()) {
            throw new DukeException(err);
        }
        try {
            targetIndex = Integer.parseInt(taskNum);
        } catch (Exception e) {
            throw new DukeException(wrongFormat);
        }
        if (targetIndex > size || targetIndex <= 0) {
            throw new DukeException(wrongNumber);
        } else {
            Task curr = list.getTask(targetIndex - 1);
            list.deleteTask(targetIndex - 1);
            response = Ui.showDeleteResponse(curr.toString(), size - 1);
        }
        try {
            storage.writeToFile(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Hint to stop the bot from running.
     *
     * @return false to not stop the bot from running
     */
    public boolean isExit() {
        return false;
    }
}
