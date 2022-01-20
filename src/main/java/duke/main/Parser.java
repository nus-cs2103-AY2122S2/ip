package duke.main;

import duke.commands.*;


public class Parser {
    // logic that determines how Burp will reply
    public static Command parseCommands(Ui.Reply type, TaskList toDoList, String cmd) throws DukeException {
        String[] cmd_split = cmd.split(" ");
        switch (type) {
        case LIST:
            return new ListCommand(toDoList, cmd);
        case TODO:
            return new AddToDoCommand(toDoList, cmd, Duke.storage);
        case DEADLINE:
            return new AddDeadlineCommand(toDoList, cmd, Duke.storage);
        case EVENT:
            return new AddEventCommand(toDoList, cmd, Duke.storage);
        case MARK:
            return new MarkCommand(toDoList, Integer.parseInt(cmd_split[1]) - 1);
        case UNMARK:
            return new UnmarkCommand(toDoList, Integer.parseInt(cmd_split[1])-1);
        case DELETE:
            return new DeleteCommand(toDoList, Integer.parseInt(cmd_split[1])-1);
        default:
            return new WrongCommand();
        }
    }
    public static String formatMsg(String msg) {
        String tabbedLine = "\t----------------------------------------------";
        return (tabbedLine + "\n\t" + msg + "\n" + tabbedLine);
    }
}
