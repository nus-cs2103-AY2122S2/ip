package main;

import Commands.*;
import Tasks.Task;


public class Parser {

    // logic that determines how Burp will reply
    public static void parseCommands(Ui.Reply type, TaskList toDoList, String cmd) {
        String[] cmd_split = cmd.split(" ");
        switch (type) {
        case LIST:
            new ListCommand(toDoList, cmd);
            break;
        case TODO:
            new AddToDoCommand(toDoList, cmd);
            break;
        case DEADLINE:
            new AddDeadlineCommand(toDoList, cmd);
            break;
        case EVENT:
            new AddEventCommand(toDoList, cmd);
            break;
        case MARK:
            new MarkCommand(toDoList, Integer.parseInt(cmd_split[1]) - 1);
            break;
        case UNMARK:
            new UnmarkCommand(toDoList, Integer.parseInt(cmd_split[1])-1);
            break;
        case DELETE:
            new DeleteCommand(toDoList, Integer.parseInt(cmd_split[1])-1);
            break;
        default:
            System.out.println(new DukeException(formatMsg("☹ OOPS!!! I'm sorry, but I don't know what that means :-(")));
            break;
        }
    }

    public static String formatMsg(String msg) {
        String tabbedLine = "\t----------------------------------------------";
        return (tabbedLine + "\n\t" + msg + "\n" + tabbedLine);
    }
}
