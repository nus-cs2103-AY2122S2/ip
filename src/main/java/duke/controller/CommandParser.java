package duke.controller;

import duke.command.*;
import duke.exception.*;
import duke.utils.Pair;

import java.time.LocalDateTime;


/**
 * class for parse user's input
 */
public class CommandParser implements Parser<TypeCommand> {
    private static final String TIME_DELIMITER = "/";
    private String input;

    public CommandParser(String input) {
        this.input = input;
    }

    @Override
    public TypeCommand parse() throws DukeException {
        String[] commandAndRest = input.trim().split("\\s+", 2);
        assert commandAndRest.length > 0 : "the command is empty";
        String command = commandAndRest[0];

        for (Command commandType : Command.values()) {
            if (commandType.matchPattern(command)) {
                switch (commandType) {
                    case EXIT:
                        return handleSimpleMetaCommand(Command.EXIT);
                    case LIST:
                        return handleSimpleMetaCommand(Command.LIST);
                    case DONE:
                        return handleNumberContentMetaCommand(Command.DONE, commandAndRest);
                    case TODO:
                        return handleContentMetaCommand(Command.TODO, commandAndRest);
                    case DEADLINE:
                        return handleTimeMetaCommand(Command.DEADLINE, commandAndRest);
                    case EVENT:
                        return handleDurationMetaCommand(Command.EVENT, commandAndRest);
                    case DELETE:
                        return handleNumberContentMetaCommand(Command.DELETE, commandAndRest);
                    case FIND:
                        return handleContentMetaCommand(Command.FIND, commandAndRest);
                    case HELP:
                        return handleSimpleMetaCommand(Command.HELP);
                    default:
                        break;
                }
            }
        }
        throw new InvalidCommandException(command);
    }

    private TypeCommand handleSimpleMetaCommand(Command type) {
        TypeCommand mc = new TypeCommand();
        mc.setType(type);
        return mc;
    }

    private TypeCommand handleContentMetaCommand(Command type, String[] input) throws DukeException {
        DescriptionCommand cmc = new DescriptionCommand();
        cmc.setType(type);
        try {
            cmc.setDescription(input[1]);
        } catch (IndexOutOfBoundsException e) {
            switch (type) {
                case TODO:
                    throw new EmptyCommandException("Description cannot be null", "Todo");
                case FIND:
                    throw new EmptyCommandException("no query body", "FIND");
                default:
                    throw new DukeException("unspecified exception");
            }
        }
        return cmc;
    }

    private TypeCommand handleNumberContentMetaCommand(Command type, String[] input) throws DukeException {
        DescriptionCommand cmc = new DescriptionCommand();
        cmc.setType(type);
        try {
            String idxStr = input[1].trim();
            //make sure the content is in the correct format
            int idx = Integer.parseInt(idxStr);
            cmc.setDescription(idxStr);
        } catch (IndexOutOfBoundsException e) {
            switch (type) {
                case DELETE:
                    throw new NoTaskTypeException("task to deletion not specified", "DELETE");
                case DONE:
                    throw new NoTaskTypeException("task to be done not specified", "DONE");
                default:
                    throw new DukeException("unspecified exception");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Content of the command is not valid. Should be in the form of integer");
        }
        return cmc;
    }

    private TypeCommand handleTimeMetaCommand(Command command, String[] input) throws DukeException {
        TimeCommand tmc = new TimeCommand();
        tmc.setType(command);
        String[] splitContent;
        //set content
        try {
            String content = input[1].trim();
            splitContent = content.split(TIME_DELIMITER, 2);
            tmc.setDescription(splitContent[0]);
            if (content.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyCommandException("Description cannot be null", "Deadline");
        }

        //set time
        try {
            LocalDateTime date = new DateParser(splitContent[1]).parse();
            tmc.setTime(date);
        } catch (IndexOutOfBoundsException e) {
            throw new NoTimeException("The time cannot be empty", "Duke.Deadline");
        }
        return tmc;
    }

    private TypeCommand handleDurationMetaCommand(Command command, String[] input) throws DukeException {
        DurationCommand dmc = new DurationCommand();
        dmc.setType(command);
        String[] splitInput;
        //set content
        try {
            String content = input[1].trim();
            splitInput = content.split(TIME_DELIMITER, 2);
            if (content.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            dmc.setDescription(splitInput[0]);
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyCommandException("Description cannot be null", "Event");
        }

        //set start and end time
        try {
            Pair<LocalDateTime, LocalDateTime> duration = new DurationParser(splitInput[1]).parse();
            dmc.setStartTime(duration.getFirst());
            dmc.setEndTime(duration.getSecond());
        } catch (IndexOutOfBoundsException e) {
            throw new NoTimeException("The time cannot be empty", "Event");
        }

        return dmc;
    }
}
