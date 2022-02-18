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
 * DeadlineTaskParser parses a string into a add deadline line task command.
 */
public class DeadlineTaskParser extends DatedTaskParser {
    private static final String regexString = "^(.*s?)\\s/by\\s(.*s?)";
    private static final String splitRegex = "\\s/by\\s";

    /**
     * Constructor for a DeadlineTaskParser
     * @param input raw string to be parsed
     */
    public DeadlineTaskParser(String input) {
        super(input, regexString);
    }

    /**
     * Parses raw string into an AddCommand that adds a DeadlineTask.
     * @return add command that adds the a deadline task with the fields
     * @throws ParserException if input of string fails to match with regex
     * @throws TaskException if task creator fails to create task with input fields
     */
    @Override
    public Command parse() throws ParserException, TaskException {
        if (!validateInput()) {
            throw new ParserException(ParserExceptionMessages.AddDeadlineParseError);
        }
        String[] split = splitAndTrimInput(splitRegex);
        String name = split[0];
        String dateString = split[1];
        if (!isValidSplits(split)) {
            throw new ParserException(ParserExceptionMessages.AddDeadlineParseError);
        }
        Date date = parseDate(dateString);
        return new AddCommand(TaskCreator.create(TaskType.Deadline, false, name, date));
    }
}
