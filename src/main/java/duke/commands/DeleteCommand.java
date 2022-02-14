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
    private final String err = "Oh no! Which task do you wish to delete? Try again :)\n";
    private final String wrongNumber = "Oh no! This task number does not exist. Try again :)\n";
    private final String wrongFormat = "Oh no! Please do not spell out the number. Try again :)\n";
    private String response = "";
    private int targetIndex;
    private int size;

    /**
     * Constructor that initialises this command to run.
     *
     * @param list task list to be delete from
     * @param echo the input details by user to delete a task
     * @param storage store of which list to delete from
     * @throws DukeException
     */
    public DeleteCommand(TaskList list, String[] echo, Storage storage) {
        this.list = list;
        this.echo = echo;
        this.storage = storage;
        this.size = list.getSize();
    }

    /**
     * Runs the deletion of task.
     *
     * @throws DukeException when the task to delete cannot be found
     */
    public String execute() throws DukeException {
        checkValidity(echo.length == 1);
        String taskNum = echo[1];
        checkValidity(taskNum.isEmpty());
        setTargetIndex(taskNum);
        deleteTaskIndex();
        saveToFile(list);
        return response;
    }
    private void deleteTaskIndex() throws DukeException {
        if (targetIndex > size || targetIndex <= 0) {
            assert false : "The task to delete is not present";
            throw new DukeException(wrongNumber);
        } else {
            Task curr = list.getTask(targetIndex - 1);
            list.deleteTask(targetIndex - 1);
            response = Ui.showDeleteResponse(curr.toString(), size - 1);
            assert response != null;
        }
    }
    private void setTargetIndex(String taskNum) throws DukeException {
        try {
            targetIndex = Integer.parseInt(taskNum);
        } catch (Exception e) {
            assert false : "The task to delete is not specified in words";
            throw new DukeException(wrongFormat);
        }
    }
    private void checkValidity(boolean bool) throws DukeException {
        if (bool) {
            assert false : "The task to delete is not specified";
            throw new DukeException(err);
        }
    }
    private void saveToFile(TaskList list) {
        try {
            storage.writeToFile(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
