package Commands;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import main.DukeException;
import main.TaskList;
import main.Parser;

public class MarkCommand {
    public MarkCommand(TaskList toDoList, int numberToMark) {
        this.runCommand(toDoList, numberToMark);
    }

    public void runCommand(TaskList toDoList, int numberToMark) {
        try {
            Task taskToMark = toDoList.get(numberToMark);
            taskToMark.mark();
            System.out.println(Parser.formatMsg("OK, I've marked this task as done:\n\t" + taskToMark));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException(Parser.formatMsg("☹ OOPS!!! Item to mark does not exist.")));
        }
    }
}
