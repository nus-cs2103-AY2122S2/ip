package main.java.ari.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import main.java.ari.command.ByeCommand;
import main.java.ari.command.Command;
import main.java.ari.command.DeadlineCommand;
import main.java.ari.command.DeleteCommand;
import main.java.ari.command.EventCommand;
import main.java.ari.command.IncorrectCommand;
import main.java.ari.command.ListCommand;
import main.java.ari.command.MarkCommand;
import main.java.ari.command.TodoCommand;
import main.java.ari.command.UnmarkCommand;
import main.java.ari.exception.CommandFormatException;
import main.java.ari.exception.EmptyCommandException;

public class Parser { // deals with making sense of the USER command
    public Parser() {

    }

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
        default:
            return new IncorrectCommand("I am sorry Master, I am afraid I do not know what you mean");
        }
    }

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

    public String[] fileBreakdown(String fileCommand) {
        String[] words = fileCommand.split(" ");
        String[] returnCommands = new String[3];

        returnCommands[0] = words[0]; // commands
        returnCommands[1] = words[1]; // mark or not mark
        returnCommands[2] = String.join(" ", Arrays.copyOfRange(words, 2, words.length)); // description

        return returnCommands;
    }

    private String getArgument(String command) throws EmptyCommandException {
        String[] cmdArray = command.split(" ");

        if (cmdArray.length == 1) {
            throw new EmptyCommandException();
        }

        return command.substring(command.indexOf(' ') + 1);
    }

    private Command prepareMark(String arg) {
        try {
            return new MarkCommand(Integer.parseInt(getArgument(arg)));
        } catch (EmptyCommandException emptyEx) {
            return new IncorrectCommand(emptyEx.getMessage());
        } catch (NumberFormatException numEx) {
            return new IncorrectCommand("Sorry Master, you have to enter an integer after the \"mark\" command");
        }
    }

    private Command prepareUnmark(String arg) {
        try {
            return new UnmarkCommand(Integer.parseInt(getArgument(arg)));
        } catch (EmptyCommandException emptyEx) {
            return new IncorrectCommand(emptyEx.getMessage());
        } catch (NumberFormatException numEx) {
            return new IncorrectCommand("Sorry Master, you have to enter an integer after the \"unmark\" command");
        }
    }

    private Command prepareTodo(String arg) {
        try {
            return new TodoCommand(getArgument(arg));
        } catch (EmptyCommandException emptyEx) {
            return new IncorrectCommand(emptyEx.getMessage());
        }
    }

    private Command prepareDeadline(String arg) {
        try {
            String[] taskTime = splitBy(getArgument(arg));
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

    private Command prepareEvent(String arg) {
        try {
            String[] taskTime = splitAt(getArgument(arg));
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

    private Command prepareDelete(String arg) {
        try {
            return new DeleteCommand(Integer.parseInt(getArgument(arg)));
        } catch (EmptyCommandException emptyEx) {
            return new IncorrectCommand(emptyEx.getMessage());
        } catch (NumberFormatException numEx) {
            return new IncorrectCommand("Sorry Master, you have to enter an integer after the \"delete\" command");
        }
    }

    private String[] splitBy(String arg) throws CommandFormatException {
        String[] returnArr = arg.split("/by");

        if (returnArr.length == 1) {
            throw new CommandFormatException("deadline _Item Description_ /by _Deadline_");
        }

        for (int i = 0; i < returnArr.length; i++) {
            returnArr[i] = returnArr[i].stripLeading().stripTrailing();
        }

        return returnArr;
    }

    private String[] splitAt(String arg) throws CommandFormatException {
        String[] returnArr = arg.split("/at");

        if (returnArr.length == 1) {
            throw new CommandFormatException("event _Item Description_ /at _Event Date or Time_");
        }

        for (int i = 0; i < returnArr.length; i++) {
            returnArr[i] = returnArr[i].stripLeading().stripTrailing();
        }

        return returnArr;
    }

    private LocalDate checkDateFormat(String arg) throws DateTimeParseException {
        try {
            return LocalDate.parse(arg);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Dear Master, please enter the date using \"YYYY-MM-DD\" format",
                    e.getParsedString(), e.getErrorIndex());
        }
    }

}
