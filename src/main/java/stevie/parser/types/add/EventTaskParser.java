package stevie.parser.types.add;

import java.util.Date;

import stevie.command.AddCommand;
import stevie.command.Command;
import stevie.exception.ParserException;
import stevie.exception.TaskException;
import stevie.exception.messages.ParserExceptionMessages;
import stevie.task.TaskCreator;
import stevie.task.types.TaskType;

/**
 * EventTaskParser parses a raw string into an AddCommand that adds an EventTask.
 */
public class EventTaskParser extends DatedTaskParser {
    private static final String regexString = "^(.*s?)\\s/at\\s(.*s?)";
    private static final String splitRegex = "\\s/at\\s";

    /**
     * Constructor for an EventTaskParser.
     * @param input raw string to be parsed
     */
    public EventTaskParser(String input) {
        super(input, regexString);
    }

    /**
     * Parses raw string into an AddCommand that adds an EventTask.
     * @return add command that adds the an event task with the fields
     * @throws ParserException if input of string fails to match with regex
     * @throws TaskException if task creator fails to create task with input fields
     */
    @Override
    public Command parse() throws ParserException, TaskException {
        if (!validateInput()) {
            throw new ParserException(ParserExceptionMessages.AddEventParseError);
        }
        String[] split = splitAndTrimInput(splitRegex);
        String name = split[0];
        String dateString = split[1];
        if (!isValidSplits(split)) {
            throw new ParserException(ParserExceptionMessages.AddEventParseError);
        }
        Date date = parseDate(dateString);
        return new AddCommand(TaskCreator.create(TaskType.Event, false, name, date));
    }
}
