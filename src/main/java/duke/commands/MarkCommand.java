package duke.commands;

import duke.commands.Command;

import java.io.IOException;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * Command class mark the task specified as done.
 */
public class MarkCommand extends Command<String> {
    private final TaskList list;
    private final String[] echo;
    private final Storage storage;

    /**
     * Constructor for this class to know where to mark.
     *
     * @param list the list from which to get the task
     * @param echo the input of user to mark a specific task
     * @param storage store of where the task list can be found
     * @throws DukeException
     */
    public MarkCommand(TaskList list, String[] echo, Storage storage) throws DukeException {
        this.list = list;
        this.echo = echo;
        this.storage = storage;
        execute();
    }

    /**
     * Execution of marking the task.
     *
     * @throws DukeException thrown when the input format is wrong
     */
    private void execute() throws DukeException {
        String err = "Oh no! Which task do you wish to mark? Try again :)\n";
        String wrongNumber = "Oh no! This task number does not exist. Try again :)\n";
        String wrongFormat = "Oh no! Please do not spell out the number. Try again :)\n";
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
            list.markTask(targetIndex - 1);
            String status = curr.getStatus();
            String description = curr.getDescription();
            Ui.showMarkRes(status, description);
        }
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
    public boolean isExit(){
        return false;
    }
}
