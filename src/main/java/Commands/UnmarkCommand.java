package Commands;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import main.DukeException;
import main.TaskList;
import main.Parser;

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
