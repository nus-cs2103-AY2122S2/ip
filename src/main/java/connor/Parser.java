package connor;

import connor.command.AddCommand;
import connor.command.ByeCommand;
import connor.command.ChangeStatusCommand;
import connor.command.ClearCommand;
import connor.command.Command;
import connor.command.CommandType;
import connor.command.DeleteCommand;
import connor.command.FindCommand;
import connor.command.ListCommand;
import connor.command.SortCommand;
import connor.task.SortType;
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
    private static final String ERROR_INVALID_SORT_TYPE = "Error! Invalid type of sort!\n"
            + "Sort commands available: 'time', 'type'";

    private CommandType commandType;
    private Command command;
    private boolean canActivate = true;
    private StringBuilder message = new StringBuilder();

    /**
     * Constructor for {@code Parser} class. Parses the user's input and creates the corresponding
     * {@code Command} if the command is valid. If the command or description of the command is invalid,
     * the {@code Parser} object will register the command as invalid and set {@code canActivate}
     * to {@code false}.
     *
     * @param s User's input.
     */
    public Parser(String s) {
        String[] statements = s.trim().concat(" ").split(" ", 2);
        String commandRaw = statements[0];
        String command = statements[0].toLowerCase();
        String desc = statements[1].trim();

        switch (command) {
        case "exit":
        case "bye": {
            setByeCommand();
            break;
        }
        case "list": {
            setListCommand();
            break;
        }
        case "todo": {
            setTodoCommand(desc);
            break;
        }
        case "deadline": {
            setDeadlineCommand(desc);
            break;
        }
        case "event": {
            setEventCommand(desc);
            break;
        }
        case "delete": {
            setDeleteCommand(desc);
            break;
        }
        case "clear": {
            setClearCommand();
            break;
        }
        case "mark": {
            setMarkCommand(desc);
            break;
        }
        case "unmark": {
            setUnmarkCommand(desc);
            break;
        }
        case "find": {
            setFindCommand(desc);
            break;
        }
        case "sort": {
            setSortCommand(desc);
            break;
        }
        default: {
            setUnknownCommand(commandRaw);
        }
        }
    }

    private void setByeCommand() {
        this.commandType = CommandType.BYE;
        this.command = new ByeCommand();
    }

    private void setListCommand() {
        this.commandType = CommandType.LIST;
        this.command = new ListCommand();
    }

    private void setTodoCommand(String desc) {
        this.commandType = CommandType.ADD;
        this.command = new AddCommand(TaskType.TODO, desc);
    }

    private void setDeadlineCommand(String desc) {
        this.commandType = CommandType.ADD;
        this.command = new AddCommand(TaskType.DEADLINE, desc);
    }

    private void setEventCommand(String desc) {
        this.commandType = CommandType.ADD;
        this.command = new AddCommand(TaskType.EVENT, desc);
    }

    private void setDeleteCommand(String desc) {
        this.commandType = CommandType.DELETE;
        try {
            int taskNo = Integer.parseInt(desc) - 1;
            this.command = new DeleteCommand(taskNo);
        } catch (NumberFormatException e) {
            setError(message, ERROR_INDEX_NOT_INTEGER);
        }
    }

    private void setClearCommand() {
        this.commandType = CommandType.CLEAR;
        this.command = new ClearCommand();
    }

    private void setMarkCommand(String desc) {
        this.commandType = CommandType.CHANGE_STATUS;
        try {
            int taskNo = Integer.parseInt(desc) - 1;
            this.command = new ChangeStatusCommand(TaskStatus.MARK, taskNo);
        } catch (NumberFormatException e) {
            setError(message, ERROR_INDEX_NOT_INTEGER);
        }
    }

    private void setUnmarkCommand(String desc) {
        this.commandType = CommandType.CHANGE_STATUS;
        try {
            int taskNo = Integer.parseInt(desc) - 1;
            this.command = new ChangeStatusCommand(TaskStatus.UNMARK, taskNo);
        } catch (NumberFormatException e) {
            setError(message, ERROR_INDEX_NOT_INTEGER);
        }
    }

    private void setFindCommand(String desc) {
        this.commandType = CommandType.FIND;
        this.command = new FindCommand(desc);
    }

    private void setSortCommand(String desc) {
        this.commandType = CommandType.SORT;
        switch (desc.toLowerCase().trim()) {
        case "time": {
            this.command = new SortCommand(SortType.TIME);
            break;
        }
        case "type": {
            this.command = new SortCommand(SortType.TYPE);
            break;
        }
        default: {
            setError(message, ERROR_INVALID_SORT_TYPE);
        }
        }
    }

    private void setUnknownCommand(String commandRaw) {
        this.commandType = CommandType.UNKNOWN;
        setError(message, ERROR_INVALID_COMMAND_START + commandRaw + ERROR_INVALID_COMMAND_END);
    }

    private void setError(StringBuilder sb, String errorMessage) {
        System.out.println(errorMessage);
        sb.append(errorMessage);
        canActivate = false;
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

    /**
     * Parses the command, if it is a valid command.
     */
    public String parse() {
        if (!canActivate) {
            return message.toString();
        }
        return command.activate();
    }

}
