package duke.util;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
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
                        System.out.println("\t____________________________________________________________");
                        System.out.println("\t☹ Woof Woof!!! This task cannot be found with my Wonka eyes!!!");
                        System.out.println("\t____________________________________________________________");
                    }
                    break;
                case UNMARK:
                    try {
                        String unmarkStr = tokens[1];
                        int taskNumUnmark = Integer.parseInt(unmarkStr) - 1;
                        command = new UnmarkCommand(taskNumUnmark);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("\t____________________________________________________________");
                        System.out.println("\t☹ Woof Woof!!! This task cannot be found with my Wonka eyes!!!");
                        System.out.println("\t____________________________________________________________");
                    }
                    break;
                case TODO:
                    Todo todo = new Todo(name);
                    command = new AddCommand(todo);
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
                        System.out.println("\t____________________________________________________________");
                        System.out.println("\t☹ Please specify a date!!");
                        System.out.println("\t____________________________________________________________");
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
                        System.out.println("\t____________________________________________________________");
                        System.out.println("\t☹ Please specify a date!!");
                        System.out.println("\t____________________________________________________________");
                    }
                    break;
                case DELETE:
                    try {
                        int taskNum = Integer.parseInt(tokens[1]) - 1;
                        command = new DeleteCommand(taskNum);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("\t____________________________________________________________");
                        System.out.println("\t☹ Woof Woof!!! Please specify a task to be deleted!!!");
                        System.out.println("\t____________________________________________________________");
                    }
                    break;
                case FIND:
                    command = new FindCommand(name);
                    break;
                default:
                    throw new InvalidCommandException("\t☹ Woof Woof!!! This command is unidentifiable!!!");
                }
            } catch (DukeException e) {
                System.out.println("\t____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("\t____________________________________________________________");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\t____________________________________________________________");
            System.out.println("\t ☹ Woof Woof!!! You haven't given me enough information for this action!!!");
            System.out.println("\t____________________________________________________________");
        }
        return command;
    }
}
