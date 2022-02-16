package seedu.duke.command;

import seedu.duke.chatbot.Storage;
import seedu.duke.chatbot.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.IncompleteCommandException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

/**
 * Executes the deleting of a {@link Task} from {@link TaskList}.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a DeleteCommand object.
     * @param index which is the index of the {@link Task} to delete in {@link TaskList}
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     *{@inheritDoc}.
     */
    @Override
    public TaskList execute(TaskList taskList, Storage storage) throws DukeException {
        if (index > taskList.getNumberOfTasks() - 1 || index < 0) {
            throw new IncompleteCommandException();
        }
        Task taskToRemove = taskList.getTasks().get(index);
        TaskList newTaskList = taskList.delete(index);
        String lineToRemove = storage.createSummaryFromTask(taskToRemove);
        storage.convertTaskListToFile(newTaskList);
        return newTaskList;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getResponseAfterCommand(TaskList taskList) {
        return Ui.showDeleteResult(taskList);
    }

    /**
     *{@inheritDoc}.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
