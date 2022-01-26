package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {
    private final static String NO_INPUT = "You need to put the task number you want to delete!";
    private final static String INVALID_INPUT = "Invalid input, you need to give a number/integer";

    public DeleteCommand(String key) {
        super(key);
    }

    @Override
    public void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        int taskIndex = 0;
        String taskDesc = getTaskDescription(input, NO_INPUT);

        try {
            taskIndex = Integer.parseInt(taskDesc) - 1;
        } catch (NumberFormatException error) {
            throw new DukeException(INVALID_INPUT);
        }

        Task task = taskList.removeTask(taskIndex);
        storage.saveList(taskList.getTaskList());
        ui.printResponse("Got it, task has been removed: \n" + task.toString() + "\nNow you have "
                + String.valueOf(taskList.getTaskListSize()) + " tasks in your list.");
    }
}
