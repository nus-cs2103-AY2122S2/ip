package connor;

import connor.command.AddCommand;
import connor.command.ByeCommand;
import connor.command.ChangeStatusCommand;
import connor.command.ClearCommand;
import connor.command.Command;
import connor.command.CommandType;
import connor.command.DeleteCommand;
import connor.command.ListCommand;
import connor.task.TaskStatus;
import connor.task.TaskType;

/**
 * Parses the user's input and activates the correct command.
 *
 * @author jaysmyname
 */
public class Parser {
    private static final String ERROR_INDEX_NOT_INTEGER = "Error! Index must be a valid integer.";
    private static final String ERROR_INVALID_COMMAND_START = "My apologies, I don't understand what '";
    private static final String ERROR_INVALID_COMMAND_END = "' means.";

    CommandType ct;
    Command command;
    boolean canActivate = true;

    /**
     * Constructor for {@code Parser} class. Parses the user's input and creates the corresponding
     * {@code Command} if the command is valid. If the command or description of the command is invalid,
     * the {@code Parser} object will register the command as invalid and set {@code canActivate}
     * to {@code false}.
     *
     * @param s User's input.
     */
    public Parser(String s) {
        String[] statement = s.trim().concat(" ").split(" ", 2);
        String commandRaw = statement[0];
        String command = statement[0].toLowerCase();
        String desc = statement[1].trim();
        switch (command) {
        case "exit":
        case "bye": {
            this.ct = CommandType.BYE;
            this.command = new ByeCommand();
            break;
        }
        case "list": {
            this.ct = CommandType.LIST;
            this.command = new ListCommand();
            break;
        }
        case "todo": {
            this.ct = CommandType.ADD;
            this.command = new AddCommand(TaskType.TODO, desc);
            break;
        }
        case "deadline": {
            this.ct = CommandType.ADD;
            this.command = new AddCommand(TaskType.DEADLINE, desc);
            break;
        }
        case "event": {
            this.ct = CommandType.ADD;
            this.command = new AddCommand(TaskType.EVENT, desc);
            break;
        }
        case "delete": {
            this.ct = CommandType.DELETE;
            try {
                int taskNo = Integer.parseInt(desc) - 1;
                this.command = new DeleteCommand(taskNo);
            } catch (NumberFormatException e) {
                System.out.println(ERROR_INDEX_NOT_INTEGER);
                canActivate = false;
            }
            break;
        }
        case "clear": {
            this.ct = CommandType.CLEAR;
            this.command = new ClearCommand();
            break;
        }
        case "mark": {
            this.ct = CommandType.CHANGE_STATUS;
            try {
                int taskNo = Integer.parseInt(desc) - 1;
                this.command = new ChangeStatusCommand(TaskStatus.MARK, taskNo);
            } catch (NumberFormatException e) {
                System.out.println(ERROR_INDEX_NOT_INTEGER);
                canActivate = false;
            }
            break;
        }
        case "unmark": {
            this.ct = CommandType.CHANGE_STATUS;
            try {
                int taskNo = Integer.parseInt(desc) - 1;
                this.command = new ChangeStatusCommand(TaskStatus.UNMARK, taskNo);
            } catch (NumberFormatException e) {
                System.out.println(ERROR_INDEX_NOT_INTEGER);
                canActivate = false;
            }
            break;
        }
        default: {
            this.ct = CommandType.UNKNOWN;
            System.out.println(ERROR_INVALID_COMMAND_START + commandRaw + ERROR_INVALID_COMMAND_END);
            canActivate = false;
        }
        }
    }

    /**
     * Parses the command, if it is a valid command.
     */
    public void parse() {
        if (!canActivate) {
            return;
        }
        command.activate();
    }

}
