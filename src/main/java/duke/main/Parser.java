package duke.main;


import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DuplicateCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.commands.WrongCommand;
import duke.tasks.Task;


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
        boolean isAddCommand = false;
        boolean isDuplicateTask = false;
        switch (type) {
        case DEADLINE:
            isAddCommand = true;
            break;
        case EVENT:
            isAddCommand = true;
            break;
        case TODO:
            isAddCommand = true;
            break;
        default:
            isAddCommand = false;
            break;
        }
        System.out.println(isAddCommand);

        // then check for duplicates
        if (isAddCommand) {
            // because the word immediately after deadline/event/todo is the task description
            String taskDescription = cmdSplit[1];
            for (int i = 0; i < toDoList.size(); i++) {
                Task current = toDoList.get(i);
                String currentTaskDescription = current.isDoneStatus()
                        ? current.toString().split(" ")[1] // if it is done, then there's only 1 whitespace
                        : current.toString().split(" ")[2]; // otherwise, there's 2 whitespaces so we take the second
                if (taskDescription.equals(currentTaskDescription)) {
                    isDuplicateTask = true;
                    break;
                }
            }
        }

        if (isDuplicateTask) {
            return new DuplicateCommand();
        }

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
            return new MarkCommand(toDoList, Integer.parseInt(cmdSplit[1]) - 1, storage);
        case UNMARK:
            return new UnmarkCommand(toDoList, Integer.parseInt(cmdSplit[1]) - 1, storage);
        case DELETE:
            return new DeleteCommand(toDoList, Integer.parseInt(cmdSplit[1]) - 1, storage);
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
        return (msg + "\n");
    }
}
