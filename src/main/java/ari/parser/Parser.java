package ari.parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import ari.command.ByeCommand;
import ari.command.Command;
import ari.command.DeadlineCommand;
import ari.command.DeleteCommand;
import ari.command.EventCommand;
import ari.command.FindCommand;
import ari.command.IncorrectCommand;
import ari.command.ListCommand;
import ari.command.MarkCommand;
import ari.command.TodoCommand;
import ari.command.UnmarkCommand;
import ari.command.ViewCommand;
import ari.exception.AriException;
import ari.exception.CommandFormatException;
import ari.exception.DateTimeParseException;
import ari.exception.EmptyCommandException;

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
        String[] commandArray = command.split(" ");

        String commandWord = commandArray[0].toLowerCase();

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
        case ViewCommand.COMMAND_WORD:
            return prepareView(command);
        default:
            return new IncorrectCommand("I am sorry Master, I am afraid I do not know what you mean");
        }
    }

    /**
     * Assigns methods to execute from input of save file if it exists
     *
     * @param fileInput command to breakdown and run
     * @param index current size of the taskList
     * @return command to execute
     */
    public ArrayList<Command> fileParse(String fileInput, int index) {
        String[] todos = fileBreakdown(fileInput);
        String instruction = todos[0];
        String commandString = todos[0] + " " + todos[2];

        ArrayList<Command> commands = new ArrayList<>();

        switch (instruction) {
        case TodoCommand.COMMAND_WORD:
            commands.add(prepareTodo(commandString));
            break;
        case DeadlineCommand.COMMAND_WORD:
            commands.add(prepareDeadline(commandString));
            break;
        case EventCommand.COMMAND_WORD:
            commands.add(prepareEvent(commandString));
            break;
        default:
            commands.add((new IncorrectCommand("Unknown commands in file!")));
            return commands;
        }

        if (todos[1].equals("1")) {
            commands.add(prepareMark("mark " + String.valueOf(index)));
        }
        return commands;
    }

    /**
     * Splits file command
     *
     * @param fileCommand input from file
     * @return an array containing the command, whether to mark the task and task description
     */
    public String[] fileBreakdown(String fileCommand) {
        String[] words = fileCommand.split(" ");
        String[] commandArguments = new String[3];

        commandArguments[0] = words[0]; // commands
        commandArguments[1] = words[1]; // mark or not mark
        commandArguments[2] = String.join(" ", Arrays.copyOfRange(words, 2, words.length)); // description

        return commandArguments;
    }

    /**
     * Returns the description part of the command
     *
     * @param command user input
     * @return description part of command
     * @throws EmptyCommandException if description is empty
     */
    private String getArgument(String command) throws EmptyCommandException {
        String[] commandArray = command.split(" ");

        if (commandArray.length == 1) {
            throw new EmptyCommandException();
        }
        assert commandArray.length != 0 : "Arguments should not be empty";

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
        } catch (AriException e) {
            return new IncorrectCommand(e.getMessage());
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
        } catch (AriException e) {
            return new IncorrectCommand(e.getMessage());
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
     * Returns FindCommand if desc is valid else IncorrectCommand
     *
     * @param desc description of task
     * @return FindCommand
     */
    private Command prepareView(String desc) {
        try {
            return new ViewCommand(checkDateFormat(getArgument(desc)));
        } catch (EmptyCommandException emptyEx) {
            return new IncorrectCommand(emptyEx.getMessage());
        } catch (DateTimeParseException dateEx) {
            return new IncorrectCommand(dateEx.getMessage());
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
            words[i] = words[i].strip();
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
            words[i] = words[i].strip();
        }

        return words;
    }

    /**
     * Returns LocalDate representation
     *
     * @param date date of task
     * @return LocalDate of task
     * @throws DateTimeParseException throws error when date format is incorrect
     */
    private LocalDate checkDateFormat(String date) throws DateTimeParseException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Dear Master, please enter the date using \"YYYY-MM-DD\" format");
        }
    }

}
