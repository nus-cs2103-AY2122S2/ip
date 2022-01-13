import Tasks.Task;
import Tasks.ToDo;
import Tasks.Event;
import Tasks.Deadline;

import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException, DukeException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tabbedLine = "\t----------------------------------------------";
        String cmd;
        List<Task> toDoList = new ArrayList<>();
        String welcome = formatMsg("Hello from Burp\n\tWhat can I do for you?");
        System.out.println(welcome);
        while (!(cmd = br.readLine()).equals("bye")) {
            String[] cmd_split = cmd.split(" ");
            switch (cmd_split[0]) {
                case "list":
                    // then list out the items
                    System.out.println(tabbedLine);
                    for (int i = 0; i < toDoList.size(); i++) {
                        System.out.println(i+1 + "." + toDoList.get(i).toString());
                    }
                    System.out.println(tabbedLine);
                    break;
                case "todo":
                    try {
                        ToDo newToDo = new ToDo(cmd.split("todo")[1], false);
                        toDoList.add(newToDo);
                        System.out.println(formatMsg("Got it. I've added this task:\n\t" + newToDo + "\n\tNow you have " + toDoList.size() + " tasks in the list."));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(new DukeException(formatMsg("☹ OOPS!!! The description of a todo cannot be empty.")));
                    }
                    break;
                case "deadline":
                    try {
                        String[] deadlineDetails = cmd.split("deadline")[1].split("/by");
                        String deadlineName = deadlineDetails[0];
                        String deadline = deadlineDetails[1];
                        Deadline newDeadline = new Deadline(deadlineName, false, deadline);
                        toDoList.add(newDeadline);
                        System.out.println(formatMsg("Got it. I've added this task:\n\t" + newDeadline + "\n\tNow you have " + toDoList.size() + " tasks in the list."));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(new DukeException(formatMsg("☹ OOPS!!! The description of a deadline cannot be empty.")));
                    }
                    break;
                case "event":
                    try {
                        String[] eventDetails = cmd.split("event")[1].split("/at");
                        String eventName = eventDetails[0];
                        String eventDateTime = eventDetails[1];
                        Event newEvent = new Event(eventName, false, eventDateTime);
                        toDoList.add(newEvent);
                        System.out.println(formatMsg("Got it. I've added this task:\n\t" + newEvent + "\n\tNow you have " + toDoList.size() + " tasks in the list."));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(new DukeException(formatMsg("☹ OOPS!!! The description of an event cannot be empty.")));
                    }
                    break;
                case "mark":
                    try {
                        int numberToMark = Integer.parseInt(cmd_split[1])-1; // convert to 0-based indexing
                        Task taskToMark = toDoList.get(numberToMark);
                        taskToMark.mark();
                        System.out.println(formatMsg("OK, I've marked this task as done:\n\t" + taskToMark));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(new DukeException(formatMsg("☹ OOPS!!! Item to mark does not exist.")));
                    }
                    break;
                case "unmark":
                    try {
                        int numberToUnmark = Integer.parseInt(cmd_split[1])-1; // convert to 0-based indexing
                        Task taskToUnmark = toDoList.get(numberToUnmark);
                        taskToUnmark.unmark();
                        System.out.println(formatMsg("OK, I've marked this task as not done yet:\n\t" + taskToUnmark));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(new DukeException(formatMsg("☹ OOPS!!! Item to unmark does not exist.")));
                    }
                    break;
                case "delete":
                    try {
                        int toDelete = Integer.parseInt(cmd_split[1])-1;
                        Task deletedTask = toDoList.remove(toDelete);
                        System.out.println(formatMsg("Noted. I've removed this task:\n\t" + deletedTask + "\n\tNow you have " + toDoList.size() + " tasks in the list."));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(new DukeException(formatMsg("☹ OOPS!!! Item to delete does not exist.")));
                    }
                    break;
                default:
                    System.out.println(new DukeException(formatMsg("☹ OOPS!!! I'm sorry, but I don't know what that means :-(")));
                    break;
            }
        }
        System.out.println(formatMsg("Bye. Hope to see you again soon!"));
    }

    public static String formatMsg(String msg) {
        String tabbedLine = "\t----------------------------------------------";
        return (tabbedLine + "\n\t" + msg + "\n" + tabbedLine);
    }
}
