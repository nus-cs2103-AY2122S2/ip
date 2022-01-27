package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public abstract class TaskCommand extends Command {
    private final static String TASK_DESC_EMPTY = "The description of your task cannot be empty.";

    public TaskCommand(String key) {
        super(key);
    }

    protected String getTaskDescription(String input) throws DukeException {
        return super.getTaskDescription(input, TASK_DESC_EMPTY);
    }

    protected void updateTaskList(Task newTask, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        taskList.addTask(newTask);
        storage.saveList(taskList.getTaskList());
        String printStr = "Gotcha. Added the task: \n   " + newTask.toString() + "\nNow you have "
                + taskList.getTaskListSize() + " tasks in your list.";

        ui.printResponse(printStr);
    }
}
