package duke.commands;

import duke.tasks.Task;
import duke.main.DukeException;
import duke.main.TaskList;
import duke.main.Parser;

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
