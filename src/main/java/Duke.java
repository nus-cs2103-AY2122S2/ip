import Tasks.Task;
import Tasks.ToDo;
import Tasks.Event;
import Tasks.Deadline;

import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;

public class Duke {
    private enum Reply {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, DEFAULT
    }

    public static void main(String[] args) throws IOException {
        List<Task> toDoList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cmd;
        // Attempt to open file. If file does not exist, then create a file
        try {
            System.out.println("Attempting to open file");
            addFileContent("./tasklist.txt", toDoList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            // create a new file called tasklist.txt in the current directory
            File f = new File("./tasklist.txt");
        }

        String welcome = formatMsg("Hello from Burp\n\tWhat can I do for you?");
        System.out.println(welcome);
        while (!(cmd = br.readLine()).equals("bye")) {
            Reply type = determineType(cmd.split(" ")[0]);
            burpReply(type, toDoList, cmd);
        }
        // do writing right before exiting
        writeFileContent(toDoList);
        System.out.println(formatMsg("Bye. Hope to see you again soon!"));
    }

    // Reads and adds the file's content into the array
    private static void addFileContent(String filePath, List<Task> toDoList) throws FileNotFoundException{
        File f = new File(filePath);
        // save commands into the file
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            // mark status & type & descriptor & additional
            String cmd = s.nextLine();
            String[] cmd_split = cmd.split("&");
            Reply type;
            boolean mark = cmd_split[0].equals("[X]") ? true : false;
            switch (cmd_split[1]) {
                case "T":
                    type = Reply.TODO;
                    break;
                case "E":
                    type = Reply.EVENT;
                    break;
                case "D":
                    type = Reply.DEADLINE;
                    break;
                default:
                   type = Reply.DEFAULT;
                   break;
            }

            switch (type) {
                case TODO:
                    // ToDo(String task, boolean markStatus)
                    toDoList.add(new ToDo(cmd_split[2], mark));
                    break;
                case EVENT:
                    // Event(String task, boolean markStatus, String dateTime)
                    toDoList.add(new Event(cmd_split[2], mark, cmd_split[3]));
                    break;
                case DEADLINE:
                    // Deadline(String task, boolean markStatus, String deadline)
                    toDoList.add(new Deadline(cmd_split[2], mark, cmd_split[3]));
                    break;
                default:
                    // do nothing
                    System.out.println("aaaa");
                    break;
            }
        }
    }

    // Writes the contents of toDoList into storage with specific formatting
    private static void writeFileContent(List<Task> toDoList) throws IOException {
        FileWriter fw = new FileWriter("./tasklist.txt");
        for (int i = 0; i < toDoList.size(); i++) {
            Task currentTask = toDoList.get(i);
            fw.write(currentTask.getStringCmd());
            fw.write("\n");
        }
        fw.close();
    }
    // logic that determines how Burp will reply
    public static void burpReply(Reply type, List<Task> toDoList, String cmd) {
        String[] cmd_split = cmd.split(" ");
        String tabbedLine = "\t----------------------------------------------";
        switch (type) {
            case LIST:
                System.out.println(tabbedLine);
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.println(i+1 + "." + toDoList.get(i).toString());
                }
                System.out.println(tabbedLine);
                break;
            case TODO:
                try {
                    ToDo newToDo = new ToDo(cmd.split("todo")[1], false);
                    toDoList.add(newToDo);
                    System.out.println(formatMsg("Got it. I've added this task:\n\t" + newToDo + "\n\tNow you have " + toDoList.size() + " tasks in the list."));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new DukeException(formatMsg("☹ OOPS!!! The description of a todo cannot be empty.")));
                }
                break;
            case DEADLINE:
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
            case EVENT:
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
            case MARK:
                try {
                    int numberToMark = Integer.parseInt(cmd_split[1])-1; // convert to 0-based indexing
                    Task taskToMark = toDoList.get(numberToMark);
                    taskToMark.mark();
                    System.out.println(formatMsg("OK, I've marked this task as done:\n\t" + taskToMark));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new DukeException(formatMsg("☹ OOPS!!! Item to mark does not exist.")));
                }
                break;
            case UNMARK:
                try {
                    int numberToUnmark = Integer.parseInt(cmd_split[1])-1; // convert to 0-based indexing
                    Task taskToUnmark = toDoList.get(numberToUnmark);
                    taskToUnmark.unmark();
                    System.out.println(formatMsg("OK, I've marked this task as not done yet:\n\t" + taskToUnmark));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new DukeException(formatMsg("☹ OOPS!!! Item to unmark does not exist.")));
                }
                break;
            case DELETE:
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

    // to determine what kind of reply Burp should give
    public static Reply determineType(String command) {
        switch (command) {
            case "list":
                return Reply.LIST;
            case "todo":
                return Reply.TODO;
            case "deadline":
                return Reply.DEADLINE;
            case "event":
                return Reply.EVENT;
            case "mark":
                return Reply.MARK;
            case "unmark":
                return Reply.UNMARK;
            case "delete":
                return Reply.DELETE;
            default:
                return Reply.DEFAULT;
        }
    }

    public static String formatMsg(String msg) {
        String tabbedLine = "\t----------------------------------------------";
        return (tabbedLine + "\n\t" + msg + "\n" + tabbedLine);
    }
}
