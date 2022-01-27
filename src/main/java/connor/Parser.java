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

public class Parser {
    private static final String ERROR_INDEX_NOT_INTEGER = "Error! Index must be a valid integer.";
    private static final String ERROR_INVALID_COMMAND_START = "My apologies, I don't understand what '";
    private static final String ERROR_INVALID_COMMAND_END = "' means.";

    private CommandType commandType;
    private Command command;
    private boolean canActivate = true;

    public Parser(String s) {
        String[] statements = s.trim().concat(" ").split(" ", 2);
        String commandRaw = statements[0];
        String command = statements[0].toLowerCase();
        String desc = statements[1].trim();
        switch (command) {
        case "exit":
        case "bye": {
            this.commandType = CommandType.BYE;
            this.command = new ByeCommand();
            break;
        }
        case "list": {
            this.commandType = CommandType.LIST;
            this.command = new ListCommand();
            break;
        }
        case "todo": {
            this.commandType = CommandType.ADD;
            this.command = new AddCommand(TaskType.TODO, desc);
            break;
        }
        case "deadline": {
            this.commandType = CommandType.ADD;
            this.command = new AddCommand(TaskType.DEADLINE, desc);
            break;
        }
        case "event": {
            this.commandType = CommandType.ADD;
            this.command = new AddCommand(TaskType.EVENT, desc);
            break;
        }
        case "delete": {
            this.commandType = CommandType.DELETE;
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
            this.commandType = CommandType.CLEAR;
            this.command = new ClearCommand();
            break;
        }
        case "mark": {
            this.commandType = CommandType.CHANGE_STATUS;
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
            this.commandType = CommandType.CHANGE_STATUS;
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
            this.commandType = CommandType.UNKNOWN;
            System.out.println(ERROR_INVALID_COMMAND_START + commandRaw + ERROR_INVALID_COMMAND_END);
            canActivate = false;
        }
        }
    }

    public void parse() {
        if (!canActivate) {
            return;
        }
        command.activate();
    }

}
