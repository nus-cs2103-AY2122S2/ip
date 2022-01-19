package duke.commands;

import duke.tasks.Task;
import duke.main.DukeException;
import duke.main.TaskList;
import duke.main.Parser;

public class UnmarkCommand extends Command<Integer> {
    public UnmarkCommand(TaskList toDoList, Integer numberToUnmark) throws DukeException {
        this.runCommand(toDoList, numberToUnmark);
    }

    public void runCommand(TaskList toDoList, Integer numberToUnmark) throws DukeException {
        try {
            Task taskToUnmark = toDoList.get(numberToUnmark);
            taskToUnmark.unmark();
            System.out.println(Parser.formatMsg("OK, I've marked this task as not done yet:\n\t" + taskToUnmark));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Parser.formatMsg("☹ OOPS!!! Item to unmark does not exist."));
        }
    }
}
