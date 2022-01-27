package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class EditTaskMarkCommand extends Command {
    private final static String NO_INPUT = "You need to put the task number you want to delete!";
    private final static String INVALID_INPUT = "Invalid input, you need to give a number/integer";
    private final static String MARK_TASK = "Nice I've marked this task as done: \n";
    private final static String UNMARK_TASK = "Alright, I've unmarked the task: \n ";
    private final boolean isMarkDoneTask;

    public EditTaskMarkCommand(String key, boolean isMarkDoneTask) {
        super(key);

        this.isMarkDoneTask = isMarkDoneTask;
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

        Task updatedTask = taskList.markTask(taskIndex, isMarkDoneTask);
        String cmdDescription = isMarkDoneTask ? MARK_TASK : UNMARK_TASK;

        ui.printResponse(cmdDescription + updatedTask.toString());
    }
}
