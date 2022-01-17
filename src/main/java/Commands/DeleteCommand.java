package Commands;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import main.DukeException;
import main.TaskList;
import main.Parser;

public class DeleteCommand {
    public DeleteCommand(TaskList toDoList, int numberToDelete) {
        this.runCommand(toDoList, numberToDelete);
    }

    public void runCommand(TaskList toDoList, int numberToDelete) {
        try {
            Task deletedTask = toDoList.remove(numberToDelete);
            System.out.println(Parser.formatMsg("Noted. I've removed this task:\n\t" + deletedTask + "\n\tNow you have " + toDoList.size() + " tasks in the list."));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException(Parser.formatMsg("☹ OOPS!!! Item to delete does not exist.")));
        }
    }
}
