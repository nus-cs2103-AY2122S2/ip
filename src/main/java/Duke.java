import Tasks.Task;
import Tasks.ToDo;
import Tasks.Event;
import Tasks.Deadline;

import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cmd;
        List<Task> toDoList = new ArrayList<>();
        String tabbedLine = "\t----------------------------------------------";
        String welcome = tabbedLine + "\n\tHello from Burp\n\tWhat can I do for you?\n" + tabbedLine;
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
                case "mark":
                    int numberToMark = Integer.parseInt(cmd_split[1])-1; // convert to 0-based indexing
                    if (toDoList.size() == 0 || numberToMark >= toDoList.size()) {
                        System.out.println("Error. Tasks.Task does not exist");
                        break;
                    }
                    Task taskToMark = toDoList.get(numberToMark);
                    taskToMark.mark();
                    System.out.println(tabbedLine + "\nNice! I've marked this task as done:");
                    System.out.println(taskToMark);
                    System.out.println(tabbedLine);
                    break;
                case "unmark":
                    int numberToUnmark = Integer.parseInt(cmd_split[1])-1; // convert to 0-based indexing
                    if (toDoList.size() == 0 || numberToUnmark >= toDoList.size()) {
                        System.out.println("Error. Tasks.Task does not exist");
                        break;
                    }
                    Task taskToUnmark = toDoList.get(numberToUnmark);
                    taskToUnmark.unmark();
                    System.out.println(tabbedLine + "\nOK, I've marked this task as not done yet:");
                    System.out.println(taskToUnmark);
                    System.out.println(tabbedLine);
                    break;
                case "todo":
                    ToDo newToDo = new ToDo(cmd.split("todo")[1], false);
                    toDoList.add(newToDo);
                    System.out.println(tabbedLine + "\n\tGot it. I've added this task:\n\t" + newToDo + "\n\tNow you have " + toDoList.size() + " tasks in the list.");
                    System.out.println(tabbedLine);
                    break;
                case "deadline":
                    String[] deadlineDetails = cmd.split("deadline")[1].split("/by");
                    String deadlineName = deadlineDetails[0];
                    String deadline = deadlineDetails[1];
                    Deadline newDeadline = new Deadline(deadlineName, false, deadline);
                    toDoList.add(newDeadline);
                    System.out.println(tabbedLine + "\n\tGot it. I've added this task:\n\t" + newDeadline + "\n\tNow you have " + toDoList.size() + " tasks in the list.");
                    System.out.println(tabbedLine);
                    break;
                case "event":
                    String[] eventDetails = cmd.split("event")[1].split("/at");
                    String eventName = eventDetails[0];
                    String eventDateTime = eventDetails[1];
                    Event newEvent = new Event(eventName, false, eventDateTime);
                    toDoList.add(newEvent);
                    System.out.println(tabbedLine + "\n\tGot it. I've added this task:\n\t" + newEvent + "\n\tNow you have " + toDoList.size() + " tasks in the list.");
                    System.out.println(tabbedLine);
                    break;
                default:
                    System.out.println("Invalid");
            }
        }
        System.out.println(tabbedLine + "\n\tBye. Hope to see you again soon!\n" + tabbedLine);
    }
}
