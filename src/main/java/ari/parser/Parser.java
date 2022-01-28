package main.java.ari.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import main.java.ari.command.*;
import main.java.ari.exception.CommandFormatException;
import main.java.ari.exception.EmptyCommandException;

/**
 * Deals with making sense of the USER command/input
 */
public class Parser {
    public Parser() {
    }

    /**
     * Assigns method to execute based on user input
     *
     * @param command user input: command and description
     * @return command to execute
     */
    public Command parse(String command) {
        String[] cmdArray = command.split(" ");

        String commandWord = cmdArray[0].toLowerCase();

        switch (commandWord) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return prepareMark(command);
        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(command);
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(command);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(command);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(command);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(command);
        case FindCommand.COMMAND_WORD:
            return prepareFind(command);
        default:
            return new IncorrectCommand("I am sorry Master, I am afraid I do not know what you mean");
        }
    }

    /**
     * Assigns methods to execute from input of save file if it exists
     *
     * @param instruction command to run
     * @param description description of task
     * @return command to execute
     */
    public Command fileParse(String instruction, String description) {
        String command = instruction + " " + description;

        switch (instruction) {
        case MarkCommand.COMMAND_WORD:
            return prepareMark(command);
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(command);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(command);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(command);
        default:
            return new IncorrectCommand("Unknown commands in file!");
        }
    }

    /**
     * Splits file command
     *
     * @param fileCommand input from file
     * @return an array containing the command, whether to mark the task and task description
     */
    public String[] fileBreakdown(String fileCommand) {
        String[] words = fileCommand.split(" ");
        String[] returnCommands = new String[3];

        returnCommands[0] = words[0]; // commands
        returnCommands[1] = words[1]; // mark or not mark
        returnCommands[2] = String.join(" ", Arrays.copyOfRange(words, 2, words.length)); // description

        return returnCommands;
    }

    /**
     * Returns the description part of the command
     *
     * @param command user input
     * @return description part of command
     * @throws EmptyCommandException if description is empty
     */
    private String getArgument(String command) throws EmptyCommandException {
        String[] cmdArray = command.split(" ");

        if (cmdArray.length == 1) {
            throw new EmptyCommandException();
        }

        return command.substring(command.indexOf(' ') + 1);
    }

    /**
     * Returns MarkCommand if desc is valid else IncorrectCommand
     *
     * @param desc description of task
     * @return MarkCommand
     */
    private Command prepareMark(String desc) {
        try {
            return new MarkCommand(Integer.parseInt(getArgument(desc)));
        } catch (EmptyCommandException emptyEx) {
            return new IncorrectCommand(emptyEx.getMessage());
        } catch (NumberFormatException numEx) {
            return new IncorrectCommand("Sorry Master, you have to enter an integer after the \"mark\" command");
        }
    }

    /**
     * Returns UnmarkCommand if desc is valid else IncorrectCommand
     *
     * @param desc description of task
     * @return UnmarkCommand
     */
    private Command prepareUnmark(String desc) {
        try {
            return new UnmarkCommand(Integer.parseInt(getArgument(desc)));
        } catch (EmptyCommandException emptyEx) {
            return new IncorrectCommand(emptyEx.getMessage());
        } catch (NumberFormatException numEx) {
            return new IncorrectCommand("Sorry Master, you have to enter an integer after the \"unmark\" command");
        }
    }

    /**
     * Returns TodoCommand if desc is valid else IncorrectCommand
     *
     * @param desc description of task
     * @return TodoCommand
     */
    private Command prepareTodo(String desc) {
        try {
            return new TodoCommand(getArgument(desc));
        } catch (EmptyCommandException emptyEx) {
            return new IncorrectCommand(emptyEx.getMessage());
        }
    }

    /**
     * Returns DeadlineCommand if desc is valid else IncorrectCommand
     *
     * @param desc description of task
     * @return DeadlineCommand
     */
    private Command prepareDeadline(String desc) {
        try {
            String[] taskTime = splitBy(getArgument(desc));
            LocalDate date = checkDateFormat(taskTime[1]);
            return new DeadlineCommand(taskTime[0], taskTime[1], date);
        } catch (EmptyCommandException emptyEx) {
            return new IncorrectCommand(emptyEx.getMessage());
        } catch (CommandFormatException cmdEx) {
            return new IncorrectCommand(cmdEx.getMessage());
        } catch (DateTimeParseException dateTimeEx) {
            return new IncorrectCommand(dateTimeEx.getMessage());
        }
    }

    /**
     * Returns EventCommand if desc is valid else IncorrectCommand
     *
     * @param desc description of task
     * @return EventCommand
     */
    private Command prepareEvent(String desc) {
        try {
            String[] taskTime = splitAt(getArgument(desc));
            LocalDate date = checkDateFormat(taskTime[1]);
            return new EventCommand(taskTime[0], taskTime[1], date);
        } catch (EmptyCommandException emptyEx) {
            return new IncorrectCommand(emptyEx.getMessage());
        } catch (CommandFormatException cmdEx) {
            return new IncorrectCommand(cmdEx.getMessage());
        } catch (DateTimeParseException dateTimeEx) {
            return new IncorrectCommand(dateTimeEx.getMessage());
        }
    }

    /**
     * Returns DeleteCommand if desc is valid else IncorrectCommand
     *
     * @param desc description of task
     * @return DeleteCommand
     */
    private Command prepareDelete(String desc) {
        try {
            return new DeleteCommand(Integer.parseInt(getArgument(desc)));
        } catch (EmptyCommandException emptyEx) {
            return new IncorrectCommand(emptyEx.getMessage());
        } catch (NumberFormatException numEx) {
            return new IncorrectCommand("Sorry Master, you have to enter an integer after the \"delete\" command");
        }
    }

    /**
     * Returns FindCommand if desc is valid else IncorrectCommand
     *
     * @param desc description of task
     * @return FindCommand
     */
    private Command prepareFind(String desc) {
        try {
            return new FindCommand(getArgument(desc));
        } catch (EmptyCommandException emptyEx) {
            return new IncorrectCommand(emptyEx.getMessage());
        }
    }

    /**
     * Returns array with item description and deadline if desc is valid else throw format exception
     *
     * @param desc description of task
     * @return array with item description and deadline
     * @throws CommandFormatException keyword "/by" is missing
     */
    private String[] splitBy(String desc) throws CommandFormatException {
        String[] words = desc.split("/by");

        if (words.length == 1) {
            throw new CommandFormatException("deadline _Item Description_ /by _Deadline_");
        }

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].stripLeading().stripTrailing();
        }

        return words;
    }

    /**
     * Returns array with item description and event data if desc is valid else throw format exception
     *
     * @param desc description of task
     * @return array with item description and deadline
     * @throws CommandFormatException keyword "/at" is missing
     */
    private String[] splitAt(String desc) throws CommandFormatException {
        String[] words = desc.split("/at");

        if (words.length == 1) {
            throw new CommandFormatException("event _Item Description_ /at _Event Date or Time_");
        }

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].stripLeading().stripTrailing();
        }

        return words;
    }

    /**
     * Returns LocalDate representation
     *
     * @param date date of task
     * @return LocalDate of task
     * @throws DateTimeParseException
     */
    private LocalDate checkDateFormat(String date) throws DateTimeParseException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Dear Master, please enter the date using \"YYYY-MM-DD\" format",
                    e.getParsedString(), e.getErrorIndex());
        }
    }

}
