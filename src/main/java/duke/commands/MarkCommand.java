package duke.commands;

import duke.tasks.Task;
import duke.main.DukeException;
import duke.main.TaskList;
import duke.main.Parser;

/**
 * MarkCommand is a Command.
 * This Command is used to Mark a certain Task as being done.
 */
public class MarkCommand extends Command<Integer> {
    /**
     * Constructor for MarkCommand.
     * When this class is instantiated, it automatically runs runCommand().
     *
     * @param toDoList     the user's List of Tasks
     * @param numberToMark the item number to mark
     * @throws DukeException when the item number to mark does not exist
     */
    public MarkCommand(TaskList toDoList, int numberToMark) throws DukeException {
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
            Task taskToMark = toDoList.get(numberToMark);
            taskToMark.mark();
            System.out.println(Parser.formatMsg("OK, I've marked this task as done:\n\t" + taskToMark));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Parser.formatMsg("☹ OOPS!!! Item to mark does not exist."));
        }
    }
}
