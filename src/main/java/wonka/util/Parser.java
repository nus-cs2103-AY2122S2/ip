package wonka.util;

import wonka.command.AddCommand;
import wonka.command.ByeCommand;
import wonka.command.Command;
import wonka.command.DeleteCommand;
import wonka.command.FindCommand;
import wonka.command.InvalidCommand;
import wonka.command.ListCommand;
import wonka.command.MarkCommand;
import wonka.command.UnmarkCommand;
import wonka.enums.CommandEnums;
import wonka.exceptions.InvalidCommandException;
import wonka.exceptions.WonkaException;
import wonka.task.Deadline;
import wonka.task.Event;
import wonka.task.FixedDurationTask;
import wonka.task.Todo;

/**
 * Utility class to parse inputs that are retrieved from user.
 */
public class Parser {
    /**
     * Returns the name of the input command
     * @param inputTokens String array of tokens split by white space
     * @return name of the input command
     */
    public static String getTaskName(String[] inputTokens) {
        String name = "";
        int sizeOfInputArr = inputTokens.length;
        for (int i = 1; i < sizeOfInputArr - 1; i++) {
            name = name.concat(inputTokens[i]);
            name = name.concat(" ");
        }
        name = name.concat(inputTokens[sizeOfInputArr - 1]);
        return name;
    }

    /**
     * Returns a command to delete a task with a given task number
     * @param taskNumStr Task number of task to be deleted.
     * @return Command to delete task.
     * @throws IndexOutOfBoundsException If there is no such task.
     */
    public static Command deleteCommand(String taskNumStr) throws IndexOutOfBoundsException {
        try {
            int taskNum = Integer.parseInt(taskNumStr) - 1;
            return new DeleteCommand(taskNum);
        } catch (IndexOutOfBoundsException e) {
            String invalidDeleteMessage = "☹ Woof Woof!!! Please specify a task to be deleted!!!";
            return new InvalidCommand(invalidDeleteMessage);
        }
    }

    /**
     * Returns a command to add a deadline task with a given string which includes name and date of the deadline.
     * @param input String which includes name and date of deadline.
     * @return Command to add the deadline task.
     * @throws IndexOutOfBoundsException If date is not specified.
     */
    public static Command deadlineCommand(String input) throws IndexOutOfBoundsException {
        try {
            String[] tokens = input.split("\\s+/by\\s+");
            String date = tokens[1];
            String name = tokens[0];
            Deadline deadline = new Deadline(name, date);
            return new AddCommand(deadline);
        } catch (IndexOutOfBoundsException e) {
            String invalidDeadlineMessage = "☹ Please specify a date!!";
            return new InvalidCommand(invalidDeadlineMessage);
        }
    }

    /**
     * Returns a command to add an event task with a given string which includes name and date of the event.
     * @param input String which includes name and date of the event.
     * @return Command to add an event task.
     * @throws IndexOutOfBoundsException If date is not specified.
     */
    public static Command eventCommand(String input) throws IndexOutOfBoundsException {
        try {
            String[] tokens = input.split("\\s+/at\\s+");
            String date = tokens[1];
            String name = tokens[0];
            Event event = new Event(name, date);
            return new AddCommand(event);
        } catch (IndexOutOfBoundsException e) {
            String invalidEventMessage = "☹ Please specify a date!!";
            return new InvalidCommand(invalidEventMessage);
        }
    }

    /**
     * Returns a command to mark a task with a given task number.
     * @param taskNumStr Task number of task to be marked.
     * @return Command to mark task.
     * @throws IndexOutOfBoundsException If task number is not specified.
     */
    public static Command markCommand(String taskNumStr) throws IndexOutOfBoundsException {
        try {
            int taskNum = Integer.parseInt(taskNumStr) - 1;
            return new MarkCommand(taskNum);
        } catch (IndexOutOfBoundsException e) {
            String invalidMarkMessage = "☹ Woof Woof!!! This task cannot be found!!!";
            return new InvalidCommand(invalidMarkMessage);
        }
    }

    /**
     * Returns a command to unmark a task with a given task number.
     * @param taskNumStr Task number of task to be unmarked.
     * @return Command to unmark task.
     * @throws IndexOutOfBoundsException If task number is not specified.
     */
    public static Command unmarkCommand(String taskNumStr) throws IndexOutOfBoundsException {
        try {
            int taskNum = Integer.parseInt(taskNumStr) - 1;
            return new UnmarkCommand(taskNum);
        } catch (IndexOutOfBoundsException e) {
            String invalidUnmarkMessage = "☹ Woof Woof!!! This task cannot be found!!!";
            return new InvalidCommand(invalidUnmarkMessage);
        }
    }

    /**
     * Returns a command to add a task to be done with no given deadline.
     * @param name Name of the task.
     * @return Command to add task.
     */
    public static Command todoCommand(String name) {
        Todo todo = new Todo(name);
        return new AddCommand(todo);
    }

    /**
     * Returns a command to add a task with a fixed duration.
     * @param input String which includes name and duration of task.
     * @return Command to add the task.
     * @throws IndexOutOfBoundsException If there is no duration specified.
     */
    public static Command fixedCommand(String input) throws IndexOutOfBoundsException {
        try {
            String[] tokens = input.split("\\s+/needs\\s+");
            String name = tokens[0];
            String duration = tokens[1];
            FixedDurationTask fixed = new FixedDurationTask(name, duration);
            return new AddCommand(fixed);
        } catch (IndexOutOfBoundsException e) {
            String invalidAddMessage = "☹ Please specify a duration!!";
            return new InvalidCommand(invalidAddMessage);
        }
    }

    /**
     * Parses the String input and returns the appropriate command.
     *
     * @param input String input from user.
     * @return Appropriate Command.
     */
    public static Command parse(String input) {
        String[] tokens = input.split("\\s+");
        String commandStr = tokens[0];
        Command command;

        try {
            CommandEnums inputCommandEnums = CommandEnums.valueOf(commandStr.toUpperCase());
            int sizeOfInputArr = tokens.length;
            String name = Parser.getTaskName(tokens);
            try {
                switch (inputCommandEnums) {
                case BYE:
                    command = new ByeCommand();
                    break;
                case LIST:
                    command = new ListCommand();
                    break;
                case MARK:
                    command = Parser.markCommand(name);
                    break;
                case UNMARK:
                    command = Parser.unmarkCommand(name);
                    break;
                case TODO:
                    try {
                        if (sizeOfInputArr == 1) {
                            throw new InvalidCommandException("☹ Woof Woof!!! This command is unidentifiable!!!");
                        }
                        command = Parser.todoCommand(name);
                    } catch (InvalidCommandException e) {
                        command = new InvalidCommand(e.getMessage());
                    }
                    break;
                case EVENT:
                    command = Parser.eventCommand(name);
                    break;
                case DEADLINE:
                    command = Parser.deadlineCommand(name);
                    break;
                case DELETE:
                    command = Parser.deleteCommand(name);
                    break;
                case FIND:
                    command = new FindCommand(name);
                    break;
                case FIXED:
                    command = Parser.fixedCommand(name);
                    break;
                default:
                    throw new InvalidCommandException("\t☹ Woof Woof!!! This command is unidentifiable!!!");
                }
            } catch (WonkaException e) {
                command = new InvalidCommand(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            command = new InvalidCommand("☹ Woof Woof!!! You haven't given me enough information for this action!!!");
        }
        return command;
    }
}
