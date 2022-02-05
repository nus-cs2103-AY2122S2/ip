package duke.util;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.enums.CommandEnums;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
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
     * @param input String input from user.
     * @return Appropriate Command.
     */
    public static Command parse(String input) {
        String[] tokens = input.split(" ");
        String commandStr = tokens[0];
        Command command = null;

        try {
            CommandEnums inputCommandEnums = CommandEnums.valueOf(commandStr.toUpperCase());

            int sizeOfInputArr = tokens.length;

            String name = "";
            for (int i = 1; i < sizeOfInputArr - 1; i++) {
                name = name.concat(tokens[i]);
                name = name.concat(" ");
            }
            name = name.concat(tokens[sizeOfInputArr - 1]); // to eliminate white space at the end

            try {
                switch (inputCommandEnums) {
                case BYE:
                    command = new ByeCommand();
                    break;
                case LIST:
                    command = new ListCommand();
                    break;
                case MARK:
                    try {
                        String markStr = tokens[1];
                        int taskNumMark = Integer.parseInt(markStr) - 1;
                        command = new MarkCommand(taskNumMark);
                    } catch (IndexOutOfBoundsException e) {
                        String invalidMarkMessage = "☹ Woof Woof!!! This task cannot be found!!!";
                        command = new InvalidCommand(invalidMarkMessage);
                    }
                    break;
                case UNMARK:
                    try {
                        String unmarkStr = tokens[1];
                        int taskNumUnmark = Integer.parseInt(unmarkStr) - 1;
                        command = new UnmarkCommand(taskNumUnmark);
                    } catch (IndexOutOfBoundsException e) {
                        String invalidUnmarkMessage = "☹ Woof Woof!!! This task cannot be found!!!";
                        command = new InvalidCommand(invalidUnmarkMessage);
                    }
                    break;
                case TODO:
                    try {
                        if (sizeOfInputArr == 1) {
                            throw new InvalidCommandException("☹ Woof Woof!!! This command is unidentifiable!!!");
                        }
                        Todo todo = new Todo(name);
                        command = new AddCommand(todo);
                    } catch (InvalidCommandException e) {
                        command = new InvalidCommand(e.getMessage());
                    }
                    break;
                case EVENT:
                    try {
                        String[] tokensEvent = input.split("/at ");
                        String time = tokensEvent[1];

                        String[] tokensNameEvent = name.split("/");
                        String eventName = tokensNameEvent[0];
                        Event event = new Event(eventName, time);
                        command = new AddCommand(event);
                    } catch (IndexOutOfBoundsException e) {
                        String invalidEventMessage = "☹ Please specify a date!!";
                        command = new InvalidCommand(invalidEventMessage);
                    }
                    break;
                case DEADLINE:
                    try {
                        String[] tokensDeadline = input.split("/by ");
                        String date = tokensDeadline[1];

                        String[] tokensNameDeadline = name.split("/");
                        String deadlineName = tokensNameDeadline[0];
                        Deadline deadline = new Deadline(deadlineName, date);
                        command = new AddCommand(deadline);
                    } catch (IndexOutOfBoundsException e) {
                        String invalidDeadlineMessage = "☹ Please specify a date!!";
                        command = new InvalidCommand(invalidDeadlineMessage);
                    }
                    break;
                case DELETE:
                    try {
                        int taskNum = Integer.parseInt(tokens[1]) - 1;
                        command = new DeleteCommand(taskNum);
                    } catch (IndexOutOfBoundsException e) {
                        String invalidDeleteMessage = "☹ Woof Woof!!! Please specify a task to be deleted!!!";
                        command = new InvalidCommand(invalidDeleteMessage);
                    }
                    break;
                case FIND:
                    command = new FindCommand(name);
                    break;
                default:
                    throw new InvalidCommandException("\t☹ Woof Woof!!! This command is unidentifiable!!!");
                }
            } catch (DukeException e) {
                command = new InvalidCommand(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            command = new InvalidCommand("☹ Woof Woof!!! You haven't given me enough information for this action!!!");
        }
        return command;
    }
}
