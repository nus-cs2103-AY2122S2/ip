import java.io.IOException;
import java.time.DateTimeException;

public class Parser {

    public Parser() {
    }

    public static String formatTaskToStore(Task task) {
        String prefix;
        String data;
        if (task instanceof Deadline) {
            prefix = TaskConstant.PREFIX_DEADLINE;
            data = prefix + Message.VERTICAL_BAR_WITH_SPACE + task.getStatusIcon() + Message.VERTICAL_BAR_WITH_SPACE +
                    task.getDescription() + Message.VERTICAL_BAR_WITH_SPACE + ((Deadline) task).getBy() +
                    Message.LINE_SEPARATOR;
        } else if (task instanceof Event) {
            prefix = TaskConstant.PREFIX_EVENT;
            data = prefix + Message.VERTICAL_BAR_WITH_SPACE + task.getStatusIcon() + Message.VERTICAL_BAR_WITH_SPACE +
                    task.getDescription() + Message.VERTICAL_BAR_WITH_SPACE + ((Event) task).getAt() +
                    Message.LINE_SEPARATOR;
        } else {
            prefix = TaskConstant.PREFIX_TODO;
            data = prefix + Message.VERTICAL_BAR_WITH_SPACE + task.getStatusIcon() + Message.VERTICAL_BAR_WITH_SPACE +
                    task.getDescription() + Message.LINE_SEPARATOR;
        }
        return data;
    }

    public static Task retrieveTaskFromStoredData(String data) {
        String[] tokens = data.split(Message.VERTICAL_BAR_REGEX);
        String prefix = tokens[0];
        boolean isDone = tokens[1].equals(TaskConstant.STATUS_ICON_DONE);
        String description = tokens[2];

        Task task;

        if (prefix.equals(TaskConstant.PREFIX_DEADLINE)) {
            String by = tokens[3];
            try {
                task = new Deadline(description, by);
            } catch (DateTimeException e) {
                ExceptionHandler.handleOtherException(e);
                task = new Todo(description);
            }
        } else if (prefix.equals(TaskConstant.PREFIX_EVENT)) {
            String at = tokens[3];
            task = new Event(description, at);
        } else {
            task = new Todo(description);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;

    }

    public Command parse(String userInput) throws DukeException {
        String[] command = parseUserInput(userInput);
        String commandType = command[0];
        String commandArgument = command[1];

        switch (commandType) {
        case Constant.COMMAND_BYE:
            return new ExitCommand();
        case Constant.COMMAND_LIST:
            return new ListCommand();
        case Constant.COMMAND_MARK:
            return new MarkCommand(commandArgument);
        case Constant.COMMAND_UNMARK:
            return new UnmarkCommand(commandArgument);
        case Constant.COMMAND_DELETE:
            return new DeleteCommand(commandArgument);
        case Constant.COMMAND_TODO:
            return new AddTodoCommand(commandArgument);
        case Constant.COMMAND_DEADLINE:
            return new AddDeadlineCommand(commandArgument);
        case Constant.COMMAND_EVENT:
            return new AddEventCommand(commandArgument);
        default:
            throw new UnknownCommandException();
        }
    }

    public static String[] parseUserInput(String userInput) throws EmptyTaskException {
        final String[] args = userInput.strip().split(" ", 2);
        String command = args[0];
        boolean isCommandListOrBye = command.equals(Constant.COMMAND_BYE) || command.equals(Constant.COMMAND_LIST);

        if (args.length == 2) {
            return args;
        } else if (!isCommandListOrBye) {
            throw new EmptyTaskException();
        } else {
            return new String[] {args[0], ""};
        }
    }
}

