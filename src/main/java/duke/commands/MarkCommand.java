package duke.commands;

import duke.tasks.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
import duke.exceptions.DukeException;
/**
 * Represents a mark command recognized by the parser.
 * MarkCommand object stores the index of the task that is to be marked/unmarked. Upon
 * execution of the object, it will attempt to mark/unmark the indexed task in the
 * task list.
 */
public class MarkCommand extends Command {
    protected boolean isMark;
    protected int index;

    /**
     * Creates an instance of a MarkCommand object.
     *
     * @param index the index of the task that is to be marked/unmarked.
     * @param isMark the boolean which determines if a task is to be marked or unmarked.
     */
    public MarkCommand(int index, Boolean isMark) {
        this.isMark = isMark;
        this.index = index;
    }

    /**
     * Executes the MarkCommand object.
     *
     * @param taskList a container of existing tasks in the program, used to
     *                 acquire the task to be marked/unmarked.
     * @param io a manager that deals with interactions with the user,
     *           used to print notifications to the user.
     * @param storage a manager that deals with storing and loading of files,
     *                used to save changes to taskList to file.
     */
    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        String variance = isMark ? "done" : "undone";
        String output = "Done! I've marked this task as " + variance + "\n       ";
        io.showMessage(output + taskList.markTask(index, isMark).toString());
        storage.save(taskList);
    }
}
