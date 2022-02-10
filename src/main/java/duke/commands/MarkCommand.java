package duke.commands;

import java.io.IOException;

import duke.main.DukeException;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.tasks.Task;


/**
 * MarkCommand is a Command.
 * This Command is used to Mark a certain Task as being done.
 */
public class MarkCommand extends Command<Integer> {
    private final Storage storage;

    /**
     * Constructor for MarkCommand.
     * When this class is instantiated, it automatically runs runCommand().
     *
     * @param toDoList     the user's List of Tasks
     * @param numberToMark the item number to mark
     * @throws DukeException when the item number to mark does not exist
     */
    public MarkCommand(TaskList toDoList, int numberToMark, Storage storage) throws DukeException {
        this.storage = storage;
        this.runCommand(toDoList, numberToMark);
    }

    /**
     * Marks a Task as being done.
     * If it is already marked, this Command will have no effect.
     *
     * @param toDoList     the user's List of Tasks
     * @param numberToMark the item number to mark
     * @throws DukeException when the item number to mark does not exist
     */
    public void runCommand(TaskList toDoList, Integer numberToMark) throws DukeException {
        try {
            // Set the task as being marked.
            Task taskToMark = toDoList.get(numberToMark);
            assert taskToMark instanceof Task;
            taskToMark.mark();

            // Print out the formatted message after marking
            Ui.setDukeResponse(Parser.formatMsg("OK, I've marked this task as done:\n" + taskToMark));
            storage.writeFileContent(toDoList);
        } catch (IndexOutOfBoundsException e) {
            Ui.setDukeResponseError(Parser.formatMsg("OOPS!!! Item to mark does not exist."));
            throw new DukeException(Parser.formatMsg("OOPS!!! Item to mark does not exist."));
        } catch (IOException e) {
            throw new DukeException(Parser.formatMsg("IOException caught") + e);
        }
    }
}
