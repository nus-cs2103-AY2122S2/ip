package seedu.duke.command;


import seedu.duke.chatbot.Storage;
import seedu.duke.chatbot.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.Note;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

/**
 * Adds a note attached to a task.
 */
public class AddNoteCommand extends Command {
    private final int taskIndex;
    private final String noteContent;

    public AddNoteCommand(String noteContent, int index) {
        this.taskIndex = index;
        this.noteContent = noteContent;
    }

    /**
     *{@inheritDoc}.
     */
    @Override
    public TaskList execute(TaskList taskList, Storage storage) throws DukeException {
        Task taskToAdd = taskList.getTasks().get(taskIndex);
        Task editedTask = taskToAdd.addNoteToNoteList(new Note(noteContent));
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
        return Ui.showAddNoteResult(taskList,updatedTask);
    }

    /**
     *{@inheritDoc}.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
