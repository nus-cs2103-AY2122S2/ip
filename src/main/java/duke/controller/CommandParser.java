package duke.controller;

import duke.command.*;
import duke.exception.*;
import duke.utils.Pair;

import java.time.LocalDateTime;

/**
 * class for parse user's input
 */
public class CommandParser implements Parser<TypeCommand> {
    private static final String TIME_SPLITTER = "/";
    private String input;

    public CommandParser(String input) {
        this.input = input;
    }

    /**
     * Method to determine what type of command user input
     * @return TypeCommand
     * @throws DukeException
     */
    @Override
    public TypeCommand parse() throws DukeException {
        String[] commandUserInput = input.trim().split("\\s+", 2);
        String command = commandUserInput[0];

        for (Command commandType : Command.values()) {
            if (commandType.matchPattern(command)) {
                switch (commandType) {
                    case EXIT:
                        return handleSimpleCommand(Command.EXIT);
                    case LIST:
                        return handleSimpleCommand(Command.LIST);
                    case DONE:
                        return handleNumberContentCommand(Command.DONE, commandUserInput);
                    case TODO:
                        return handleContentCommand(Command.TODO, commandUserInput);
                    case DEADLINE:
                        return handleTimeCommand(Command.DEADLINE, commandUserInput);
                    case EVENT:
                        return handleDurationCommand(Command.EVENT, commandUserInput);
                    case DELETE:
                        return handleNumberContentCommand(Command.DELETE, commandUserInput);
                    case FIND:
                        return handleContentCommand(Command.FIND, commandUserInput);
                    case HELP:
                        return handleSimpleCommand(Command.HELP);
                    default:
                        break;
                }
            }
        }
        throw new InvalidCommandException(command);
    }

    /**
     * @param type command input
     * @return type of the command
     */
    private TypeCommand handleSimpleCommand(Command type) {
        TypeCommand command = new TypeCommand();
        command.setType(type);
        return command;
    }

    /**
     *
     * @param type type of a task
     * @param input user input command
     * @return command
     * @throws DukeException if command not specified
     */
    private TypeCommand handleContentCommand(Command type, String[] input) throws DukeException {
        DescriptionCommand command = new DescriptionCommand();
        command.setType(type);
        try {
            command.setDescription(input[1]);
        } catch (IndexOutOfBoundsException e) {
            switch (type) {
                case TODO:
                    throw new EmptyCommandException("cannot be null!", "Todo");
                case FIND:
                    throw new EmptyCommandException("cannot be null!", "Find");
                default:
                    throw new DukeException("unspecified exception");
            }
        }
        return command;
    }

    /**
     *
     * @param type command type
     * @param input user input
     * @return type of the command
     * @throws DukeException when time not specified for a task
     */
    private TypeCommand handleNumberContentCommand(Command type, String[] input) throws DukeException {
        DescriptionCommand command = new DescriptionCommand();
        command.setType(type);
        try {
            String idxStr = input[1].trim();
            command.setDescription(idxStr);
        } catch (IndexOutOfBoundsException e) {
            switch (type) {
                case DELETE:
                    throw new NoTaskTypeException("deletion not specified", "DELETE");
                case DONE:
                    throw new NoTaskTypeException("done not specified", "DONE");
                default:
                    throw new DukeException("unspecified exception");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Content is not valid.");
        }
        return command;
    }

    private TypeCommand handleTimeCommand(Command command, String[] input) throws DukeException {
        TimeCommand timeCommand = new TimeCommand();
        timeCommand.setType(command);
        String[] breakCommand;
        try {
            String description = input[1].trim();
            breakCommand = description.split(TIME_SPLITTER, 2);
            timeCommand.setDescription(breakCommand[0]);
            if (description.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyCommandException("cannot be null!", "Deadline");
        }
        try {
            LocalDateTime date = new DateParser(breakCommand[1]).parse();
            timeCommand.setTime(date);
        } catch (IndexOutOfBoundsException e) {
            throw new NoTimeException("The time cannot be empty!", "Deadline");
        }
        return timeCommand;
    }

    private TypeCommand handleDurationCommand(Command command, String[] input) throws DukeException {
        DurationCommand durationCommand = new DurationCommand();
        durationCommand.setType(command);
        String[] splitInput;
        try {
            String description = input[1].trim();
            splitInput = description.split(TIME_SPLITTER, 2);
            if (description.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            durationCommand.setDescription(splitInput[0]);
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyCommandException("Description cannot be empty!", "Event");
        }
        try {
            Pair<LocalDateTime, LocalDateTime> duration = new DurationParser(splitInput[1]).parse();
            durationCommand.setStartTime(duration.getFirst());
            durationCommand.setEndTime(duration.getSecond());
        } catch (IndexOutOfBoundsException e) {
            throw new NoTimeException("The time cannot be null!", "Event");
        }
        return durationCommand;
    }
}
