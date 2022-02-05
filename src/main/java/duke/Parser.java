package duke;

import java.time.LocalDate;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {

    public Parser() {

    }

    /**
     * Parses command line input.
     *
     * @param fullCommand String to parse.
     * @return Command instance.
     * @throws DukeException If command is not recognized.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] fullCommandArray = fullCommand.split(" ");
        String command = fullCommandArray[0];
        if (command.equals("deadline")) {
            return parseAddDeadlineCommand(fullCommand);
        } else if (command.equals("event")) {
            return parseAddEventCommand(fullCommand);
        } else if (command.equals("todo")) {
            return parseAddTodoCommand(fullCommand);
        } else if (command.equals("list")) {
            return parseListCommand();
        } else if (command.equals("delete")) {
            return parseDeleteCommand(fullCommand);
        } else if (command.equals("mark")) {
            return parseMarkCommand(fullCommand);
        } else if (command.equals("unmark")) {
            return parseUnmarkCommand(fullCommand);
        } else if (command.equals("bye")) {
            return parseExitCommand();
        } else {
            throw new DukeException("I don't recognize that command.");
        }
    }

    /**
     * Parses add deadline command.
     *
     * @param fullCommand String to parse.
     * @return AddCommand instance.
     */
    private static Command parseAddDeadlineCommand(String fullCommand) {
        String dataString = fullCommand.replaceFirst("deadline ", "");
        String[] data = dataString.split("/by ");
        Task task = new Deadline(data[0], LocalDate.parse(data[1]));
        return new AddCommand(task);
    }

    /**
     * Parses add event command.
     *
     * @param fullCommand String to parse.
     * @return AddCommand instance.
     */
    private static Command parseAddEventCommand(String fullCommand) {
        String dataString = fullCommand.replaceFirst("event ", "");
        String[] data = dataString.split("/at ");
        Task task = new Event(data[0], LocalDate.parse(data[1]));
        return new AddCommand(task);
    }

    /**
     * Parses add todo command.
     *
     * @param fullCommand String to parse.
     * @return AddCommand instance.
     * @throws DukeException If command is not recognized.
     */
    private static Command parseAddTodoCommand(String fullCommand) throws DukeException {
        String dataString = fullCommand.replaceFirst("todo ", "");
        if (dataString.trim().equals("")) {
            throw new DukeException("Todo needs a description");
        }
        Task task = new Todo(dataString);
        return new AddCommand(task);
    }

    /**
     * Parses list command.
     *
     * @param fullCommand String to parse.
     * @return ListCommand instance.
     */
    private static Command parseListCommand() {
        return new ListCommand();
    }

    /**
     * Parses delete command.
     *
     * @param fullCommand String to parse.
     * @return DeleteCommand instance.
     */
    private static Command parseDeleteCommand(String fullCommand) {
        String dataString = fullCommand.replaceFirst("delete ", "");
        int taskNumber = Integer.parseInt(dataString);
        return new DeleteCommand(taskNumber);
    }

    /**
     * Parses mark command.
     *
     * @param fullCommand String to parse.
     * @return MarkCommand instance.
     */
    private static Command parseMarkCommand(String fullCommand) {
        String dataString = fullCommand.replaceFirst("mark ", "");
        int taskNumber = Integer.parseInt(dataString);
        return new MarkCommand(taskNumber);
    }

    /**
     * Parses unmark command.
     *
     * @param fullCommand String to parse.
     * @return UnmarkCommand instance.
     */
    private static Command parseUnmarkCommand(String fullCommand) {
        String dataString = fullCommand.replaceFirst("unmark ", "");
        int taskNumber = Integer.parseInt(dataString);
        return new UnmarkCommand(taskNumber);
    }

    /**
     * Parses exit command.
     *
     * @param fullCommand String to parse.
     * @return ExitCommand instance.
     */
    private static Command parseExitCommand() {
        return new ExitCommand();
    }

}
