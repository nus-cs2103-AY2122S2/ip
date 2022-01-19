package duke.commands;

import duke.tasks.Task;
import duke.main.DukeException;
import duke.main.TaskList;
import duke.main.Parser;

public class MarkCommand extends Command<Integer> {
    public MarkCommand(TaskList toDoList, int numberToMark) throws DukeException {
        this.runCommand(toDoList, numberToMark);
    }

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
