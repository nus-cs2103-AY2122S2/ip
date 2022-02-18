package stevie.parser.types.edit;

import stevie.command.Command;
import stevie.command.DeleteCommand;
import stevie.exception.ParserException;

/**
 * DeleteParser parses a string into a command that deletes a task from the task list.
 */
public class DeleteParser extends IndexParser {
    /**
     * Constructor for DeleteParser.
     * @param input string to be parsed as an integer
     */
    public DeleteParser(String input) {
        super(input);
    }

    /**
     * Parses string into integer and passes it to constructor for a DeleteCommand.
     * @return command that deletes a task from task list based off the index given
     * @throws ParserException if string cannot be parsed as integer
     */
    @Override
    public Command parse() throws ParserException {
        return new DeleteCommand(getIndex());
    }
}
