package duke.component;

import java.time.DateTimeException;

import duke.command.FindCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.DeleteCommand;

import duke.command.ViewCommand;
import duke.constant.Message;
import duke.constant.TaskConstant;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import static duke.constant.CommandConstant.COMMAND_BYE;
import static duke.constant.CommandConstant.COMMAND_DEADLINE;
import static duke.constant.CommandConstant.COMMAND_DELETE;
import static duke.constant.CommandConstant.COMMAND_EVENT;
import static duke.constant.CommandConstant.COMMAND_FIND;
import static duke.constant.CommandConstant.COMMAND_LIST;
import static duke.constant.CommandConstant.COMMAND_MARK;
import static duke.constant.CommandConstant.COMMAND_TODO;
import static duke.constant.CommandConstant.COMMAND_UNMARK;
import static duke.constant.CommandConstant.COMMAND_VIEW;

import duke.exception.DukeException;
import duke.exception.UnknownCommandException;
import duke.exception.EmptyTaskException;

/**
 * A class to parsing user input and format task to store and retrieve task from stored data.
 */
public class Parser {

    public Parser() {
    }

    /**
     * Formats Task class to String that can be stored in a text file.
     *
     * @param task the task to be formatted
     * @return String that can be written into a text file.
     */
    public String formatTaskToStore(Task task) {
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

    /**
     * Retrieves the task from stored data.
     *
     * @param data String in one line that is stored in the text file.
     * @return Task class
     */
    public static Task retrieveTaskFromStoredData(String data) {
        String[] tokens = data.split(Message.VERTICAL_BAR_REGEX);
        String prefix = tokens[0];
        boolean isDone = tokens[1].equals(TaskConstant.STATUS_ICON_DONE);
        String description = tokens[2];

        Task task;
        ExceptionHandler exceptionHandler = new ExceptionHandler();

        if (prefix.equals(TaskConstant.PREFIX_DEADLINE)) {
            String by = tokens[3];
            try {
                task = new Deadline(description, by);
            } catch (DateTimeException e) {
                exceptionHandler.handleOtherException(e);
                task = new Todo(description);
            }
        } else if (prefix.equals(TaskConstant.PREFIX_EVENT)) {
            String at = tokens[3];
            try {
                task = new Event(description, at);
            } catch (DateTimeException e) {
                exceptionHandler.handleOtherException(e);
                task = new Todo(description);
            }
        } else {
            task = new Todo(description);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;

    }

    /**
     * Executes the command from user input.
     * @param userInput user input read by Scanner
     * @return Specific command class
     * @throws DukeException if there is unknown command
     */
    public Command parse(String userInput) throws DukeException {
        String[] command = parseUserInput(userInput);
        String commandType = command[0];
        String commandArgument = command[1];

        switch (commandType) {
        case COMMAND_BYE:
            return new ExitCommand();
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_MARK:
            return new MarkCommand(commandArgument);
        case COMMAND_UNMARK:
            return new UnmarkCommand(commandArgument);
        case COMMAND_DELETE:
            return new DeleteCommand(commandArgument);
        case COMMAND_TODO:
            return new AddTodoCommand(commandArgument);
        case COMMAND_DEADLINE:
            return new AddDeadlineCommand(commandArgument);
        case COMMAND_EVENT:
            return new AddEventCommand(commandArgument);
        case COMMAND_FIND:
            return new FindCommand(commandArgument);
        case COMMAND_VIEW:
            return new ViewCommand(commandArgument);
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses user input to find command type and other arguments.
     *
     * @param userInput user input in String
     * @return String array specifies command type and other arguments
     * @throws EmptyTaskException if there is no other arguments but the type is not bye or list.
     */
    public static String[] parseUserInput(String userInput) throws EmptyTaskException {
        final String[] args = userInput.strip().split(" ", 2);
        String command = args[0];
        boolean isCommandListOrBye = command.equals(COMMAND_BYE) || command.equals(COMMAND_LIST);

        if (args.length == 2) {
            return args;
        } else if (!isCommandListOrBye) {
            throw new EmptyTaskException();
        } else {
            return new String[] {args[0], ""};
        }
    }
}

