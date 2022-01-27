package duke.main;


import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.commands.WrongCommand;


/**
 * Parser class.
 * This class handles the parsing of all user inputs and decides what to do
 * based on the user input.
 */
public class Parser {

    /**
     * This method determines what Burp should do when the user gives an input
     *
     * @param type     the Reply type, determined by Ui
     * @param toDoList the user's List of Tasks
     * @param cmd      the user's input to Burp
     * @return Returns a Command to indicate what the last Command run was
     * @throws DukeException when a WrongCommand is given
     */
    public static Command parseCommands(Ui.Reply type, TaskList toDoList,
                                        String cmd, Storage storage) throws DukeException {
        String[] cmdSplit = cmd.split(" ");
        switch (type) {
        case LIST:
            return new ListCommand(toDoList, cmd);
        case TODO:
            return new AddToDoCommand(toDoList, cmd, storage);
        case DEADLINE:
            return new AddDeadlineCommand(toDoList, cmd, storage);
        case EVENT:
            return new AddEventCommand(toDoList, cmd, storage);
        case MARK:
            return new MarkCommand(toDoList, Integer.parseInt(cmdSplit[1]) - 1);
        case UNMARK:
            return new UnmarkCommand(toDoList, Integer.parseInt(cmdSplit[1]) - 1);
        case DELETE:
            return new DeleteCommand(toDoList, Integer.parseInt(cmdSplit[1]) - 1);
        case FIND:
            return new FindCommand(toDoList, cmd);
        case BYE:
            return new ByeCommand();
        default:
            return new WrongCommand();
        }
    }

    /**
     * Method to format strings accordingly.
     *
     * @param msg the String to be formatted
     * @return a nicely formatted String.
     */
    public static String formatMsg(String msg) {
        return ("\n" + msg + "\n");
    }
}
