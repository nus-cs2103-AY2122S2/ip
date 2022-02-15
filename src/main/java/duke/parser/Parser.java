package duke.parser;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.ShowAllCommands;
import duke.commands.UnmarkCommand;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Parser that make sense of the input by user.
 */
public class Parser {
    /**
     * Filter the input by user and act on the input.
     *
     * @param echo the input that is echoed by the user
     * @param listOfTasks list of tasks belonging to user
     * @param storage storage of the list
     * @return the command to be executed by the bot
     * @throws DukeException thrown when the input does not make sense
     */
    public static Command<String> parseInput(String echo, TaskList listOfTasks, Storage storage) throws DukeException {
        if (echo.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (echo.equalsIgnoreCase("commands")) {
            return new ShowAllCommands();
        } else if (echo.equalsIgnoreCase("list")) {
            return new ListCommand(listOfTasks);
        } else if (echo.toLowerCase().contains("todo")) {
            return new AddTodoCommand(listOfTasks, echo.split(" ", 2), storage);
        } else if (echo.toLowerCase().contains("event")) {
            return new AddEventCommand(listOfTasks, echo.split(" ", 2), storage);
        } else if (echo.toLowerCase().contains("deadline")) {
            return new AddDeadlineCommand(listOfTasks, echo.split(" ", 2), storage);
        } else if (echo.toLowerCase().contains("unmark")) {
            return new UnmarkCommand(listOfTasks, echo.split(" ", 2), storage);
        } else if (echo.toLowerCase().contains("mark")) {
            return new MarkCommand(listOfTasks, echo.split(" ", 2), storage);
        } else if (echo.toLowerCase().contains("delete")) {
            return new DeleteCommand(listOfTasks, echo.split(" ", 2), storage);
        } else if (echo.toLowerCase().contains("find")) {
            return new FindCommand(listOfTasks, echo.split(" ", 2));
        } else {
            throw new DukeException("Oh no! I fear I don't understand! Try again!\n");
        }
    }
}
