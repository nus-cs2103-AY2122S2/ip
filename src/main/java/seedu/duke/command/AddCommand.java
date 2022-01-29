package seedu.duke.command;

import seedu.duke.chatbot.Storage;
import seedu.duke.chatbot.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

/**
 * Executes the adding of {@link Task} to {@link TaskList}.
 */
public class AddCommand extends Command {
    private final Task taskToAdd;

    /**
     * Creates a AddCommand.
     * @param taskToAdd is the {@link Task} to be added to the {@link TaskList}
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd =  taskToAdd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList newTaskList = taskList.add(taskToAdd);
        ui.showAddResult(newTaskList,taskToAdd);
        String lineToAdd = storage.createSummaryFromTask(this.taskToAdd);
        storage.addLine(storage.getFilePath(), lineToAdd);
        return newTaskList;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}