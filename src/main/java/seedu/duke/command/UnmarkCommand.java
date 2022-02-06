package seedu.duke.command;

import seedu.duke.chatbot.Storage;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.chatbot.Ui;

/**
 * Created when user wants to unmark a {@link Task} from done to undone.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates a UnmarkCommand object.
     * @param index which is the index of the {@link Task} to unmark
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList newTaskList = taskList.unmark(this.index);
        storage.convertTaskListToFile(newTaskList);
        return newTaskList;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getResponseAfterCommand(TaskList taskList) {
        Task unmarkedTask = taskList.getTasks().get(this.index);
        return Ui.showUnmarkedResult(unmarkedTask);
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
