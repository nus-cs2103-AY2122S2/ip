package Commands;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.ToDo;
import main.DukeException;
import main.TaskList;
import main.Parser;

public class AddDeadlineCommand {
    public AddDeadlineCommand(TaskList toDoList, String cmd) {
        this.runCommand(toDoList, cmd);
    }

    public void runCommand(TaskList toDoList, String cmd) {
        try {
            String[] deadlineDetails = cmd.split("deadline")[1].split("/by");
            String deadlineName = deadlineDetails[0];
            String deadline = deadlineDetails[1];
            Deadline newDeadline = new Deadline(deadlineName, false, deadline);
            toDoList.add(newDeadline);
            System.out.println(Parser.formatMsg("Got it. I've added this task:\n\t" + newDeadline + "\n\tNow you have " + toDoList.size() + " tasks in the list."));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException(Parser.formatMsg("☹ OOPS!!! The description of a deadline cannot be empty.")));
        }
    }

    ;
}
