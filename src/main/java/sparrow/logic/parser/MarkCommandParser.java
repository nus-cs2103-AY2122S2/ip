package sparrow.logic.parser;

import sparrow.commons.exceptions.ParseException;
import sparrow.logic.commands.MarkCommand;

public class MarkCommandParser implements Parser<MarkCommand> {
    /**
     * Parses the arguments in the context of a DeleteCommand and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public MarkCommand parse(String args) throws ParseException {
        int index = ParserUtil.parseIndex(args);
        return new MarkCommand(index);
    }
}
