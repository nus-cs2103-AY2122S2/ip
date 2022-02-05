package duke.commands;

import duke.exceptions.DukeException;
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
     * Creates a MarkCommand object. Specifies that a mark command requires storing
     * of data to file.
     *
     * @param isMark determines whether to mark or unmark a task upon command execution.
     */
    public MarkCommand(boolean isMark) {
        modifyData = true;
        exitProgram = false;
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
     * @return a String that notifies the user that the task has been marked/unmarked.
     * @throws DukeException when the index provided by the user is invalid.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        String variance = isMark ? "done" : "undone";
        String output = "Done! I've marked this task as " + variance + "\n    ";
        return output + taskList.markTask(index, isMark);
    }
}
