package duke.util;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
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

    /**
     * Parses the String input and returns the appropriate command.
     *
     * @param userInput String input from user.
     * @return Appropriate Command.
     */
    public static Command parseCommand(String userInput) throws DukeException {

        String[] splitCommand = userInput.split(" ", 2);
        String commandWord = splitCommand[0].toLowerCase();
        Command command = null;

        switch (commandWord) {
        case (ByeCommand.COMMAND_WORD):
            command = new ByeCommand();
            break;
        case (DeleteCommand.COMMAND_WORD):
            if (splitCommand.length == 1) {
                throw new DukeException("\tAaaarrrrgggghhhh you can't delete nuthin' matey!\n");
            }
            int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            command = new DeleteCommand(taskIndex);
            break;
        case (FindCommand.COMMAND_WORD):
            if (splitCommand.length == 1) {
                throw new DukeException("\tAaaarrrrgggghhhh you can't find nuthin' matey!\n");
            }
            command = new FindCommand(splitCommand[1]);
            break;
        case (ListCommand.COMMAND_WORD):
            command = new ListCommand();
            break;
        case (MarkCommand.COMMAND_WORD):
            if (splitCommand.length == 1) {
                throw new DukeException("\tAaaarrrrgggghhhh you can't mark nuthin' matey!\n");
            }
            taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            command = new MarkCommand(taskIndex);
            break;
        case (UnmarkCommand.COMMAND_WORD):
            if (splitCommand.length == 1) {
                throw new DukeException("\tAaaarrrrgggghhhh you can't unmark nuthin' matey!\n");
            }
            taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            command = new UnmarkCommand(taskIndex);
            break;
        case ("deadline"):
            if (splitCommand.length == 1) {
                throw new DukeException("\tAaaarrrrgggghhhh deadline description can't be empty matey!\n");
            }
            String[] furtherSplitCommand = splitCommand[1].split(" /by ", 2);
            try {
                Deadline deadline = new Deadline(furtherSplitCommand[0], furtherSplitCommand[1]);
                command = new AddCommand(deadline);
            } catch (DukeException e) {
                command = new AddCommand(null);
            }
            break;
        case ("event"):
            if (splitCommand.length == 1) {
                throw new DukeException("\tAaaarrrrgggghhhh event description can't be empty matey!\n");
            }
            furtherSplitCommand = splitCommand[1].split(" /at ", 2);
            try {
                Event event = new Event(furtherSplitCommand[0], furtherSplitCommand[1]);
                command = new AddCommand(event);
            } catch (DukeException e) {
                command = new AddCommand(null);
            }
            break;
        case ("todo"):
            if (splitCommand.length == 1) {
                throw new DukeException("\tAaaarrrrgggghhhh todo description can't be empty matey!\n");
            }
            command = new AddCommand(new Todo(splitCommand[1]));
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry matey, but I don't know what that means :-(\n");
        }
        return command;
    }
}
