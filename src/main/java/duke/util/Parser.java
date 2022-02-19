package duke.util;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Utility class to parse inputs that are retrieved from user.
 */
public class Parser {

    private static final String SAD_FACE = "\uD83D\uDE1E";

    /**
     * Parses the String input and returns the appropriate command.
     *
     * @param userInput String input from user.
     * @return Appropriate Command.
     */
    public static Command parseCommand(String userInput) throws DukeException {

        String[] splitCommand = userInput.split(" ", 2);
        String commandWord = splitCommand[0].toLowerCase();

        switch (commandWord) {
        case (ByeCommand.COMMAND_WORD):
            return new ByeCommand();
        case (DeleteCommand.COMMAND_WORD):
            return prepareDelete(splitCommand);
        case (FindCommand.COMMAND_WORD):
            return prepareFind(splitCommand);
        case (HelpCommand.COMMAND_WORD):
            return new HelpCommand();
        case (ListCommand.COMMAND_WORD):
            return new ListCommand();
        case (MarkCommand.COMMAND_WORD):
            return prepareMark(splitCommand);
        case (UnmarkCommand.COMMAND_WORD):
            return prepareUnmark(splitCommand);
        case ("deadline"):
            return prepareDeadline(splitCommand);
        case ("event"):
            return prepareEvent(splitCommand);
        case ("todo"):
            return prepareToDo(splitCommand);
        default:
            throw new DukeException("OOPS!!! I'm sorry matey, but I don't know what that means " + SAD_FACE + "\n");
        }
    }

    /**
     * Parses arguments in the context of the delete command
     *
     * @param splitCommand args full command args string
     * @return the prepared command
     * @throws DukeException the input error
     */
    private static Command prepareDelete(String[] splitCommand) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("Aaaarrrrgggghhhh you can't delete nuthin' matey!\n");
        }
        int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
        return new DeleteCommand(taskIndex);
    }

    /**
     * Parses arguments in the context of the find command
     *
     * @param splitCommand args full command args string
     * @return the prepared command
     * @throws DukeException the input error
     */
    private static Command prepareFind(String[] splitCommand) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("Aaaarrrrgggghhhh you can't find nuthin' matey!\n");
        }
        return new FindCommand(splitCommand[1]);
    }

    /**
     * Parses arguments in the context of the mark command
     *
     * @param splitCommand args full command args string
     * @return the prepared command
     * @throws DukeException the input error
     */
    private static Command prepareMark(String[] splitCommand) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("Aaaarrrrgggghhhh you can't mark nuthin' matey!\n");
        }
        int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
        return new MarkCommand(taskIndex);
    }

    /**
     * Parses arguments in the context of the unmark command
     *
     * @param splitCommand args full command args string
     * @return the prepared command
     * @throws DukeException the input error
     */
    private static Command prepareUnmark(String[] splitCommand) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("Aaaarrrrgggghhhh you can't unmark nuthin' matey!\n");
        }
        int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
        return new UnmarkCommand(taskIndex);
    }

    /**
     * Parses arguments in the context of the Add command and adds a new Deadline task
     *
     * @param splitCommand args full command args string
     * @return the prepared command
     * @throws DukeException the input error
     */
    private static Command prepareDeadline(String[] splitCommand) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("Aaaarrrrgggghhhh deadline description can't be empty matey!\n");
        }
        String[] furtherSplitCommand = splitCommand[1].split(" /by ", 2);
        try {
            Deadline deadline = new Deadline(furtherSplitCommand[0], furtherSplitCommand[1]);
            return new AddCommand(deadline);
        } catch (DukeException e) {
            return new AddCommand(null);
        }
    }

    /**
     * Parses arguments in the context of the Add command and adds a new Event task
     *
     * @param splitCommand args full command args string
     * @return the prepared command
     * @throws DukeException the input error
     */
    private static Command prepareEvent(String[] splitCommand) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("Aaaarrrrgggghhhh event description can't be empty matey!\n");
        }
        String[] furtherSplitCommand = splitCommand[1].split(" /at ", 2);
        try {
            Event event = new Event(furtherSplitCommand[0], furtherSplitCommand[1]);
            return new AddCommand(event);
        } catch (DukeException e) {
            return new AddCommand(null);
        }
    }

    /**
     * Parses arguments in the context of the Add command and adds a new ToDo task
     *
     * @param splitCommand args full command args string
     * @return the prepared command
     * @throws DukeException the input error
     */
    private static Command prepareToDo(String[] splitCommand) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("Aaaarrrrgggghhhh todo description can't be empty matey!\n");
        }
        return new AddCommand(new Todo(splitCommand[1]));
    }
}
