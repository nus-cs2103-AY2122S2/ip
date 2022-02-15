package duke.commands;

import java.io.IOException;
import java.util.regex.Pattern;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command class mark the task specified as not done.
 */
public class UnmarkCommand extends Command<String> {
    private final TaskList list;
    private final String[] echo;
    private final Storage storage;
    private final String err = "Oh no! Which task do you wish to unmark? Try again :)\n";
    private final String wrongNumber = "Oh no! This task number does not exist. Try again :)\n";
    private final String wrongFormat = "Oh no! Please do not spell out the number. Try again :)\n";
    private String response = "";
    private int targetIndex;
    private final int size;

    /**
     * Constructor for this class to know where to unmark.
     *
     * @param list the list from which to get the task
     * @param echo the input of user to unmark a specific task
     * @param storage store of where the task list can be found
     */
    public UnmarkCommand(TaskList list, String[] echo, Storage storage) {
        this.list = list;
        this.echo = echo;
        this.storage = storage;
        this.size = list.getSize();
    }

    /**
     * Execution of unmarking the task.
     *
     * @throws DukeException thrown when the input format is wrong
     */
    public String execute() throws DukeException {
        checkValidity(echo.length == 1);
        String taskNum = echo[1];
        checkValidity(taskNum.isEmpty());
        setTargetIndex(taskNum);
        unmarkTaskIndex();
        saveToFile(list);
        return response;
    }
    private void unmarkTaskIndex() throws DukeException {
        if (targetIndex > size || targetIndex <= 0) {
            assert targetIndex > size : "Task to unmark is not in list";
            throw new DukeException(wrongNumber);
        } else {
            Task curr = list.getTask(targetIndex - 1);
            curr.unMark();
            String status = curr.getStatus();
            String description = curr.getDescription();
            assert description != null;
            response = Ui.showUnmarkRes(status, description);
            assert response != null;
        }
    }
    private void setTargetIndex(String taskNum) throws DukeException {
        try {
            targetIndex = Integer.parseInt(taskNum);
        } catch (Exception e) {
            assert Pattern.matches("[a-zA-Z]+", taskNum) : "Task to unmark is not specified in words";
            throw new DukeException(wrongFormat);
        }
    }
    private void checkValidity(boolean bool) throws DukeException {
        if (bool) {
            assert bool : "Task to unmark is not specified";
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
