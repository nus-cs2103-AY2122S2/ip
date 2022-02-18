package duke.util;

import java.util.Arrays;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.PrioritiseCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;

/**
 * Represents the parser for user commands.
 */
public class Parser {
    /**
     * Parses the command.
     * @param command The command.
     * @return A Command object.
     * @throws DukeException when the command is invalid.
     */
    public static Command parse(String command) throws DukeException {
        switch (command) {
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "todo":
            throw new DukeException("The description of a todo cannot be empty.");
        default:
            String[] commandArr = command.split(" ");
            if (commandArr.length > 1) {
                String type = commandArr[0];
                String description = String.join(" ", Arrays.copyOfRange(commandArr, 1, commandArr.length));
                switch (type) {
                case "find":
                    return new FindCommand(description);
                case "todo":
                    return new ToDoCommand(description);
                case "deadline":
                    String[] deadlineArr = description.split(" /by ");
                    String deadlineDesc = deadlineArr[0];
                    String by = deadlineArr[1];
                    return new DeadlineCommand(deadlineDesc, by);
                case "event":
                    String[] eventArr = description.split(" /at ");
                    String eventDesc = eventArr[0];
                    String at = eventArr[1];
                    return new EventCommand(eventDesc, at);
                default:
                    int index = Integer.parseInt(description.split(" ")[0]) - 1;
                    switch (type) {
                    case "mark":
                        return new MarkCommand(index);
                    case "unmark":
                        return new UnmarkCommand(index);
                    case "delete":
                        return new DeleteCommand(index);
                    case "prioritise":
                        Priority priority = parsePriority(commandArr[2]);
                        return new PrioritiseCommand(index, priority);
                    default:
                        throw new DukeException("Sorry, but I don't know what that means :-(");
                    }
                }
            } else {
                throw new DukeException("Sorry, but I don't know what that means :-(");
            }
        }
    }
    /**
     * Parses the input into a task priority.
     * @param s The input.
     * @return A task priority.
     */
    public static Priority parsePriority(String s) {
        switch(s) {
        case "low":
            return Priority.LOW;
        case "high":
            return Priority.HIGH;
        default:
            return Priority.MEDIUM;
        }
    }
}
