package seedu.duke.command;

import seedu.duke.chatbot.Storage;
import seedu.duke.chatbot.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class ShowNoteCommand extends Command {
    private final int taskIndex;

    public ShowNoteCommand(int index) {
        this.taskIndex = index;
    }

    /**
     *{@inheritDoc}.
     */
    @Override
    public TaskList execute(TaskList taskList, Storage storage) throws DukeException {
        return taskList; //this command is to print a result and not to modify the taskList
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getResponseAfterCommand(TaskList taskList) {
        Task taskToShow = taskList.getTasks().get(taskIndex);
        return Ui.showNote(taskList,taskToShow);
    }

    /**
     *{@inheritDoc}.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
