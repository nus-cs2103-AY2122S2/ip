package duke.commands;

import duke.tasks.Event;
import duke.main.DukeException;
import duke.main.TaskList;
import duke.main.Parser;

public class AddEventCommand extends Command<String> {
    public AddEventCommand(TaskList toDoList, String cmd) throws DukeException {
        this.runCommand(toDoList, cmd);
    }

    public void runCommand(TaskList toDoList, String cmd) throws DukeException {
        try {
            String[] eventDetails = cmd.split("event")[1].split("/at");
            String eventName = eventDetails[0];
            String eventDateTime = eventDetails[1];
            Event newEvent = new Event(eventName, false, eventDateTime);
            toDoList.add(newEvent);
            System.out.println(Parser.formatMsg("Got it. I've added this task:\n\t" + newEvent + "\n\tNow you have " + toDoList.size() + " tasks in the list."));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Parser.formatMsg("☹ OOPS!!! The description of an event cannot be empty."));
        }
    }

    ;
}
