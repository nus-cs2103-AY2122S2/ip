package duke.commands;

import duke.tasks.Deadline;
import duke.main.DukeException;
import duke.main.TaskList;
import duke.main.Parser;

public class AddDeadlineCommand extends Command<String> {
    public AddDeadlineCommand(TaskList toDoList, String cmd) throws DukeException {
        this.runCommand(toDoList, cmd);
    }

    public void runCommand(TaskList toDoList, String cmd) throws DukeException {
        try {
            String[] deadlineDetails = cmd.split("deadline")[1].split("/by");
            String deadlineName = deadlineDetails[0];
            String deadline = deadlineDetails[1];
            Deadline newDeadline = new Deadline(deadlineName, false, deadline);
            toDoList.add(newDeadline);
            System.out.println(Parser.formatMsg("Got it. I've added this task:\n\t" + newDeadline + "\n\tNow you have " + toDoList.size() + " tasks in the list."));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Parser.formatMsg("☹ OOPS!!! The description of a deadline cannot be empty."));
        }
    }

    ;
}
