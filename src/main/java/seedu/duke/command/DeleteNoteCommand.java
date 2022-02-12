package seedu.duke.command;

import seedu.duke.chatbot.Storage;
import seedu.duke.chatbot.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

/**
 * Deletes a note attached to a task.
 */
public class DeleteNoteCommand extends Command {
    private final int taskIndex;
    private final int noteIndex;

    public DeleteNoteCommand(int taskIndex, int noteIndex) {
        this.taskIndex = taskIndex;
        this.noteIndex = noteIndex;
    }

    /**
     *{@inheritDoc}.
     */
    @Override
    public TaskList execute(TaskList taskList, Storage storage) throws DukeException {
        Task taskToAdd = taskList.getTasks().get(taskIndex);
        Task editedTask = taskToAdd.deleteNoteFromNoteList(noteIndex);
        TaskList newTaskList = taskList.replace(taskIndex, editedTask);
        //storage.convertTaskListToFile(newTaskList);
        return newTaskList;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getResponseAfterCommand(TaskList taskList) {
        Task updatedTask = taskList.getTasks().get(taskIndex);
        return Ui.showDeleteNoteResult(taskList,updatedTask);
    }

    /**
     *{@inheritDoc}.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
