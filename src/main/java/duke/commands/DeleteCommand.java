package duke.commands;

import duke.tasks.Task;
import duke.main.DukeException;
import duke.main.TaskList;
import duke.main.Parser;

public class DeleteCommand extends Command<Integer> {
    public DeleteCommand(TaskList toDoList, Integer numberToDelete) {
        this.runCommand(toDoList, numberToDelete);
    }

    public void runCommand(TaskList toDoList, Integer numberToDelete) {
        try {
            Task deletedTask = toDoList.remove(numberToDelete);
            System.out.println(Parser.formatMsg("Noted. I've removed this task:\n\t" + deletedTask + "\n\tNow you have " + toDoList.size() + " tasks in the list."));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException(Parser.formatMsg("☹ OOPS!!! Item to delete does not exist.")));
        }
    }
}
