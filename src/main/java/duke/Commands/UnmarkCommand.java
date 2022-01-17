package duke.Commands;

import duke.Tasks.Task;
import duke.main.DukeException;
import duke.main.TaskList;
import duke.main.Parser;

public class UnmarkCommand {
    public UnmarkCommand(TaskList toDoList, int numberToUnmark) {
        this.runCommand(toDoList, numberToUnmark);
    }

    public void runCommand(TaskList toDoList, int numberToUnmark) {
        try {
            Task taskToUnmark = toDoList.get(numberToUnmark);
            taskToUnmark.unmark();
            System.out.println(Parser.formatMsg("OK, I've marked this task as not done yet:\n\t" + taskToUnmark));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException(Parser.formatMsg("☹ OOPS!!! Item to unmark does not exist.")));
        }
    }
}
