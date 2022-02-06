package seedu.duke.command;

import seedu.duke.chatbot.Storage;
import seedu.duke.chatbot.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

/**
 * Created when user wants to mark a {@link seedu.duke.task.Task} as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a MarkCommand object.
     * @param index which is the index of the {@link seedu.duke.task.Task} to mark
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * {inheritDoc}.
     * @throws DukeException if the index of task is invalid or database cannot be updated
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList newTaskList = taskList.mark(this.index);
        storage.convertTaskListToFile(newTaskList);
        return newTaskList;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getResponseAfterCommand(TaskList taskList) {
        Task markedTask = taskList.getTasks().get(this.index);
        return Ui.showMarkedResult(markedTask);
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}