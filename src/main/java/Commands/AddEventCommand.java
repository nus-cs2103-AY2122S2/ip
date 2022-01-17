package Commands;

import Tasks.Event;
import Tasks.ToDo;
import main.DukeException;
import main.TaskList;
import main.Parser;

public class AddEventCommand {
    public AddEventCommand(TaskList toDoList, String cmd) {
        this.runCommand(toDoList, cmd);
    }

    public void runCommand(TaskList toDoList, String cmd) {
        try {
            String[] eventDetails = cmd.split("event")[1].split("/at");
            String eventName = eventDetails[0];
            String eventDateTime = eventDetails[1];
            Event newEvent = new Event(eventName, false, eventDateTime);
            toDoList.add(newEvent);
            System.out.println(Parser.formatMsg("Got it. I've added this task:\n\t" + newEvent + "\n\tNow you have " + toDoList.size() + " tasks in the list."));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException(Parser.formatMsg("☹ OOPS!!! The description of an event cannot be empty.")));
        }
    }

    ;
}
