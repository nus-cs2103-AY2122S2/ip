package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.Storage;
import duke.managers.Ui;
import duke.tasks.TaskList;

/**
 * Represents a mark command recognized by the parser.
 * It stores the index of the task that is to be marked/unmarked. Upon
 * execution of the object, it will attempt to mark/unmark the indexed task in the
 * task list.
 */
public class MarkCommand extends Command {

    protected boolean isMark;
    protected int index;

    /**
     * Creates a MarkCommand object.
     *
     * @param isMark determines whether to mark or unmark a task upon command execution.
     */
    public MarkCommand(boolean isMark) {
        this.isMark = isMark;
    }

    /**
     * Handles user input and stores the index of the task that
     * is to be marked/unmarked.
     *
     * @param tokens a String array that represents the user input.
     * @throws DukeException when the input provided is not a valid index.
     */
    @Override
    public void handleParam(String[] tokens) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (Exception exception) {
            throw new DukeException("Invalid input! Please enter the number of the task you want to mark/unmark.");
        }
        this.index = index;
    }

    /**
     * Returns a boolean that specifies whether the user input matches the Command.
     *
     * @return a boolean that indicates whether this object is the correct Command.
     */
    @Override
    public boolean checkIdentifier(String input) {
        if (isMark) {
            return input.equals("mark");
        } else {
            return input.equals("unmark");
        }
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
     * @throws DukeException when the index provided by the user is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        String variance = isMark ? "done" : "undone";
        String output = "Done! I've marked this task as " + variance + "\n       ";
        io.showMessage(output + taskList.markTask(index, isMark));
        storage.save(taskList);
    }
}
