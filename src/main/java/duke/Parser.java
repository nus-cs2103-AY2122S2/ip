package duke;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import java.time.LocalDate;

public class Parser {

    public Parser() {

    }

    /**
     * Parses command line input.
     *
     * @param fullCommand String to parse.
     * @throws DukeException If command is not recognized.
     * @return Command instance.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] fullCommandArray = fullCommand.split(" ");
        String command = fullCommandArray[0];
        if (command.equals("deadline")) {
            return parseAddDeadlineCommand(fullCommand);
        } else if (command.equals("event")) {
            return parseAddEventCommand(fullCommand);
        } else if (command.equals("todo")) {
            return parseTodoCommand(fullCommand);
        } else if (command.equals("list")) {
            return new parseListCommand();
        } else if (command.equals("delete")) {
            return parseDeleteCommand(fullCommand);
        } else if (command.equals("mark")) {
            return parseMarkCommand(fullCommand);
        } else if (command.equals("unmark")) {
            return parseUnmarkCommand(fullCommand);
        } else if (command.equals("bye")) {
            return new ExitCommand();
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
    public Command parseAddDeadlineCommand(String fullCommand) {
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
    public Command parseAddEventCommand(String fullCommand) {
        String dataString = fullCommand.replaceFirst("event ", "");
        String[] data = dataString.split("/at ");
        Task task = new Event(data[0], LocalDate.parse(data[1]));
        return new AddCommand(task);
    }

    /**
     * Parses add todo command.
     *
     * @param fullCommand String to parse.
     * @throws DukeException If command is not recognized.
     * @return AddCommand instance.
     */
    public Command parseAddTodoCommand(String fullCommand) throws DukeException {
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
    public Command parseListCommand() {
        return new ListCommand();
    }

    /**
     * Parses delete command.
     *
     * @param fullCommand String to parse.
     * @return DeleteCommand instance.
     */
    public Command parseDeleteCommand(String fullCommand) {
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
    public Command parseMarkCommand(String fullCommand) {
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
    public Command parseUnmarkCommand(String fullCommand) {
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
    public Command parseExitCommand() {
        return new ExitCommand();
    }

}